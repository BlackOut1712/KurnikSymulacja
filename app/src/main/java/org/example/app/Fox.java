package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;;

public class Fox extends Animal{
    public static final String sign = Map.RED+"L"+Map.RESET;
    private int numberOfPreys;
    private double min_damage;
    private double max_damage;

    public Fox(){
        super();
        this.setHP(100);
        this.setSign(sign);
        this.setVision(8);
        this.min_damage = 30;
        this.max_damage = 100;
    }

    public Fox(double mdamage, double Mdamage){
        super();
        this.setHP(100);
        this.setSign(sign);
        this.setVision(8);
        this.min_damage = mdamage;
        this.max_damage = Mdamage;
    }

    private double CountDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

    private void attack(){
        ArrayList<ArrayList<Integer>> Hens_in_attack_range = checkSurroudings(1);
        if(Hens_in_attack_range == null || Hens_in_attack_range.size() == 0){
            return;     //nothing to attack
        }
        
        int random_index = (int) (Math.random() * Hens_in_attack_range.size()); //Obranie na cel losowej kury, gdy lis jest obok kilku kur.
        
        int prey_X = Hens_in_attack_range.get(random_index).get(0);
        int prey_Y = Hens_in_attack_range.get(random_index).get(1);

        
        for(Iterator<Hen> iterator = Gameplay.getHens().iterator(); iterator.hasNext();){
            Hen kura = iterator.next();
            if(kura.X == prey_X && kura.Y == prey_Y){
                if(kura.getDamage(CountDamage(), iterator)<=0){
                    numberOfPreys++;
                    System.out.println("Kura ("+kura.X +","+kura.Y+") nie zyje.");
                }
            }
        }

    }
    public int preys(){
        return numberOfPreys;
    }

    public boolean Hunt(){
        if(this.RUN()){
            this.attack();
            return true;
        }
        return false;
    }   

    public void MakeAMove(){
        for(int i=0; i<2; i++){
        if(!Hunt()){
            move();
        }
        }
    }
}