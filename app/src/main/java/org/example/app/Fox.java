package org.example.app;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;;

public class Fox extends Animal{
    //private int numberOfPreys;
    private double min_damage;
    private double max_damage;

    public Fox(){
        super();
        this.setHP(100);
        this.setSign('L');
        this.setVision(8);
        this.min_damage = 30;
        this.max_damage = 100;
    }

    public void attack(){
        ArrayList<ArrayList<Integer>> Hens_in_attack_range = checkSurroudings(1);
        if(Hens_in_attack_range == null || Hens_in_attack_range.size() == 0){
            return;     //nothing to attack
        }
        
        int random_index = (int) (Math.random() * Hens_in_attack_range.size()); //Obranie na cel losowej kury, gdy lis jest obok kilku kur.
        
        int prey_X = Hens_in_attack_range.get(random_index).get(0);
        int prey_Y = Hens_in_attack_range.get(random_index).get(1);

        
        for(Iterator<hen> iterator = gameplay.getHens().iterator(); iterator.hasNext();){
            hen kura = iterator.next();
            if(kura.X == prey_X && kura.Y == prey_Y){
                if(kura.getDamage(CountDamage(), iterator)<=0){
                    System.out.println("Kura ("+kura.X +","+kura.Y+") nie zyje.");
                }
            }
        }

    }

    public void Hunt(){
        this.RUN();
        this.attack();
    }   

    private double CountDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

}