package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;

public class Fox extends Animal{
    public static final String sign = Map.RED+"L"+Map.RESET;
    private static int HensEaten = 0;
    private static int AnimalsEatenClock = 0;
    private int numberOfPreys;          //Parametr miał służyć do wyznaczania MVP wśród lisów, ale nie zdążyłem.
    private double min_damage;
    private double max_damage;
    private boolean isStunned = false;
    private boolean isAttacked = false;

    public Fox(){
        super();
        Map.set(this.X, this.Y, sign);
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
        /*  funkcja attack() odpowiada za atakowanie drugiego zwierzecia. Zwraca true, gdy atak doszedł do skutku, oraz false, gdy nie ma nikogo w zasięgu. 
         *  W przypadku gdy obiektem wywołującym jest pies, "triggeruje" on lisa, by zaczął walczyć z nim, a nie atakował kury.*/

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
                        if(zwierze instanceof Hen){
                            HensEaten++;
                        }
                        if(Gameplay.getLogsSetting()) System.out.println(Gameplay.determineSpecies(zwierze) + " ("+zwierze.X +","+zwierze.Y+") nie zyje.");
                    }
                    break;
                }
            }
        }
        return true;
    }

    private double countDamage(){
        /* funkcja countDamage() jest funkcją kalkulującą obrażenia w przedziale (minimalne_obrazenia,maksymalne_obrazenia) oraz zwracającą ich wartość.*/
        double damage = Math.random()*(this.max_damage - this.min_damage)+min_damage;
        return damage;
    } 

    public static int getAnimalsEaten(){
        /* Parametr AnimalsEaten() liczy zjedzone zwierzeta, by kontrolowac powiekszanie sie populacji*/
        return AnimalsEatenClock;
    }

    public static int getHensEaten(){
        /* Funkcja powstała na potrzeby badań (klasy Research), brak udziału w symulacji */
        return HensEaten;
    }

    public boolean isAttacked(){
        return isAttacked;
    }

    public boolean Hunt(){
        /*  Funkcja Hunt() odpowiada za polowanie. Obiekt priorytetowo ma wykonac atak na ofiarę, jeśli taka znajduje się koło niego.
         *  Jeśli nie, ma wykonać ruch w jej stronę i jeśli znajdzie się koło niej, wykonać atak. Zwraca true, gdy atak dojdzie do skutku.
            Zwraca false gdy nie znajdzie nikogo w polu widzenia. */ 
        if(this.attack()){      
            return true;
        }

        if(this.run()){        
            this.attack();
            return true;
        }
        return false;
    }  

    public void makeAMove(){

        if(Gameplay.isDay()){       //Jeśli jest dzień, pomiń turę
            return;
        }
        if(isStunned){              //Jeśli lis jest ogłuszony, zdejmij ogłuszenie i pomiń turę.
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

    public static void reset(){
    /* Funkcja powstała na potrzeby badań (klasy Research), brak udziału w symulacji */
        AnimalsEatenClock = 0;
        HensEaten = 0;
    }

    public static void setAnimalsEaten(int number){
        /*Funkcja setAnimalsEaten() odpowiada za aktualizacje parametru zwiekszajacego populacje, po zwiększeniu populacji (patrz Gameplay.foxReproduction())*/
        AnimalsEatenClock = number;
    }

    public void setAttacked(){
        isAttacked = true;
    }

    public void stun(){
        isStunned = true;
    }




}