package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;

public class Fox extends Animal{
    public static final String sign = Map.RED+"L"+Map.RESET;
    private static int AnimalsEatenClock = 0;
    private int numberOfPreys;
    private double min_damage;
    private double max_damage;
    private boolean isStunned = false;
    private boolean isAttacked = false;

    public Fox(){
        super();
        this.setHP(100);
        this.setSign(sign);
        this.setSpeed(2.5);
        this.setVision(8);
        this.min_damage = 50;
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

    private void attack(){
        ArrayList<ArrayList<Integer>> Preys_in_attack_range = checkSurroudings(1);
        if(Preys_in_attack_range == null || Preys_in_attack_range.size() == 0){
            return;
        }
        
        int random_index = (int) (Math.random() * Preys_in_attack_range.size()); //Obranie na cel losowej kury, gdy lis jest obok kilku kur.
        
        int prey_X = Preys_in_attack_range.get(random_index).get(0);
        int prey_Y = Preys_in_attack_range.get(random_index).get(1);
        
        for(ArrayList<? extends Animal> rodzaj_zwierzecia: Gameplay.getAnimals()){
            for(Iterator<? extends Animal> iterator = rodzaj_zwierzecia.iterator(); iterator.hasNext();){
                Animal zwierze = iterator.next();
                if(zwierze.X == prey_X && zwierze.Y == prey_Y){
                    if(zwierze.getDamage(countDamage(), iterator)<=0){
                        numberOfPreys++;
                        if(!(this instanceof Dog)){
                            AnimalsEatenClock++;
                        }
                        System.out.println(Gameplay.determineSpecies(zwierze) + " ("+zwierze.X +","+zwierze.Y+") nie zyje.");
                    }
                }
            }
        }
    }

    private double countDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

    public static int getAnimalsEaten(){
        return AnimalsEatenClock;
    }

    public boolean Hunt(){
        if(this.run()){
            this.attack();
            return true;
        }
        return false;
    }  

    public void makeAMove(){
        if(Gameplay.isDay()){
            return;
        }
        if(isStunned){
            isStunned = false;
            return;
        }

        if(!Hunt()){
            move();
        }
    }

    public int preys(){
        return numberOfPreys;
    }

    public static void setAnimalsEaten(int number){
        AnimalsEatenClock = number;
    }

    public void stun(){
        isStunned = true;
    }




}