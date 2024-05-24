package org.example.app;

import java.util.ArrayList;

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
        /* a gdyby tak w tej funkcji sprawdzac najblizsze otoczenie lisa, jesli jest tam jakies stworzenie (sprawdzajac przy uzyciu map.get(x,y))
         * i pozniej szukac tego stworzenia w tablicy swoich przedstawicieli? Wtedy bym wiedzial kogo konkretnie atakuje lis.
         */
        
    }

    public void Hunt(){
        this.RUN();
        ArrayList<ArrayList<Integer>> Hens_in_attack_range = checkSurroudings(1);
        if(Hens_in_attack_range == null || Hens_in_attack_range.size() == 0){
            return;     //nothing to attack
        }
        
        int random_index = (int) (Math.random() * (Hens_in_attack_range.size()+1)-0.01);
        
        int prey_X = Hens_in_attack_range.get(random_index).get(0);
        int prey_Y = Hens_in_attack_range.get(random_index).get(1);

        //for(hen kura: );
    }

    private double CountDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

}