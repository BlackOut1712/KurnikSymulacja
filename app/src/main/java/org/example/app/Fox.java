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
        this.setHP(150);
        this.setSign(sign);
        this.setVision(8);
        this.min_damage = mdamage;
        this.max_damage = Mdamage;
    }

    private boolean attack(){
        ArrayList<ArrayList<Integer>> Preys_in_attack_range = checkSurroudings(1);
        if(Preys_in_attack_range == null || Preys_in_attack_range.size() == 0){
            return false;
        }
        
        int random_index = (int) (Math.random() * Preys_in_attack_range.size()); //Obranie na cel losowej kury, gdy lis jest obok kilku kur.
        
        int prey_X = Preys_in_attack_range.get(random_index).get(0);
        int prey_Y = Preys_in_attack_range.get(random_index).get(1);
        
        for(ArrayList<? extends Animal> rodzaj_zwierzecia: Gameplay.getAnimals()){
            for(Iterator<? extends Animal> iterator = rodzaj_zwierzecia.iterator(); iterator.hasNext();){
                Animal zwierze = iterator.next();
                if(zwierze.X == prey_X && zwierze.Y == prey_Y){
                    if(this instanceof Dog){
                        ((Fox) zwierze).setAttacked();
                    }
                    if(zwierze.getDamage(countDamage(), iterator)<=0){
                        numberOfPreys++;
                        if(!(this instanceof Dog)){
                            AnimalsEatenClock++;
                        }
                        System.out.println(Gameplay.determineSpecies(zwierze) + " ("+zwierze.X +","+zwierze.Y+") nie zyje.");
                    }
                    break;
                }
            }
        }
        return true;
    }

    private double countDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

    public static int getAnimalsEaten(){
        return AnimalsEatenClock;
    }

    public boolean isAttacked(){
        return isAttacked;
    }

    public boolean Hunt(){
        if(this.attack()){      //Atak sąsiada
            return true;
        }

        if(this.run()){         //Jeśli sąsiada nie ma w pobliżu, przemieszczenie się i atak.
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
        if(isAttacked == true){
            isAttacked = false;
        }
    }

    public int preys(){
        return numberOfPreys;
    }

    public static void setAnimalsEaten(int number){
        AnimalsEatenClock = number;
    }

    public void setAttacked(){
        isAttacked = true;
    }

    public void stun(){
        isStunned = true;
    }




}