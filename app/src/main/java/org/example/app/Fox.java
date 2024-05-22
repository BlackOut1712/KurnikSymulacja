package org.example.app;

public class Fox extends Animal{
    //private int numberOfPreys;
    private double min_damage;
    private double max_damage;

    public Fox(){
        super();
        this.setHP(100);
        this.setSign('L');
        this.min_damage = 30;
        this.max_damage = 100;
    }

    public void attack(){
        /* a gdyby tak w tej funkcji sprawdzac najblizsze otoczenie lisa, jesli jest tam jakies stworzenie (sprawdzajac przy uzyciu map.get(x,y))
         * i pozniej szukac tego stworzenia w tablicy swoich przedstawicieli? Wtedy bym wiedzial kogo konkretnie atakuje lis.
         */
        
    }

    private double CountDamage(){
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

}