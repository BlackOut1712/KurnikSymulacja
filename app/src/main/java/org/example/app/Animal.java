package org.example.app;

import java.util.ArrayList;

public abstract class Animal {
    private char sign;
    private int visual_field;
    public int X=0;
    public int Y=0;
    private double maxHP;
    private double HP;
    private double speed=1;
    private boolean isAlive;

    public Animal(){
        this.isAlive = true;
    }

    public void setSign(char sign){
        this.sign = sign;
    }

    public char getSign(){
        return this.sign;
    }

    public int move(){
        if(!isAlive){
            System.out.println("Corpses don't walk... usually...");
            return -1;
        }
        
        while(true){
            double direction = Math.random();

            if(direction>=0 && direction< 0.25){
                if(moveLeft()){
                    break;
                }
            }

            else if(direction >= 0.25 && direction < 0.50){
                if(moveRight()){
                    break;
                }
            }

            else if(direction >= 0.5 && direction < 0.75){
                if(moveForward()){
                    break;
                }
            }

            else{
                if(moveBack()){
                    break;
                }
            }
        }

        return 0;
    }

    public boolean moveLeft(){
        if(this.X-1>=0                                      //check if index x-1 fits in Map;
        /*&& Map.get(this.X-1, this.Y) == 'x'*/){               //check if spot is free
            Map.set(this.X, this.Y, 'x');
            this.X-=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    public boolean moveRight(){
        if(this.X+1 < Map.getXsize()                //check if index x+1 fits in Map;
        /*&& Map.get(this.X+1, this.Y) == 'x'*/){       //check if spot is free
            Map.set(this.X, this.Y, 'x');
            this.X +=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    public boolean moveBack(){
        if(this.Y+1 < Map.getYsize()                //check if index y+1 fits in Map;
        /*&& Map.get(this.X, this.Y+1) == 'x' */){       //check if spot is free
            Map.set(this.X, this.Y, 'x');
            this.Y+=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    public boolean moveForward(){
        if(this.Y-1 >=0                                 //check if index y-1 fits in Map;
        /*&& Map.get(this.X, this.Y-1) == 'x'*/){           //check if spot is free
            Map.set(this.X, this.Y, 'x');
            this.Y-=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    public void setHP(double HP){
        if(!isAlive){
            System.out.println("Cannot set HP to the dead animal.");
            return;
        }
        this.maxHP=HP;
        this.HP = HP;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setVision(int x){
        this.visual_field = x;
    }

    public double getDamage(double damage){
        this.HP -= damage;
        checkIfDead();
        return this.HP;
    }

    private void checkIfDead(){
        if(this.HP<=0){
            this.HP = 0;
            isAlive = false;
            Map.set(this.X, this.Y, 'x');
        }
    }

    public String getStats(){
        return 
        "\nRaport"+
        "\nHP: "+this.HP+"/"+this.maxHP
        +".\nSpeed: "+this.speed
        +".\nStatus: " + ((this.isAlive) ? "ALIVE" : "DEAD")
        +".\nPosition: ("+this.X+","+this.Y+").";
                
    }

    public ArrayList<ArrayList<Integer>> checkSurroudings(){

        ArrayList<ArrayList<Integer>> Predators_Location = new ArrayList<>();

        // Y jest 0 (dla this.Y-visual_field < 0) lub this.Y-visual_field (dla this.Y-visual_field>=0), kontynuuj do pozycji This.Y+visual_field lub konca tablicy, to samo dla X
        for(int Y = Math.max(this.Y-visual_field, 0); Y<Math.min(this.Y+visual_field,Map.getYsize()); Y++){               
            for(int X= Math.max(this.X-visual_field, 0); X< Math.min(this.X+visual_field, Map.getXsize()); X++){                              
                double distance = Math.sqrt( Math.pow(X-this.X,2) + Math.pow(Y-this.Y,2));          //Policz odległość Euklidesową
                if(distance<=visual_field){                                                         
                    if(Map.get(X,Y)=='L'){                                              //Jeśli w polu widzenia znajduje się lis,
                        ArrayList<Integer> predator = new ArrayList<>();
                        predator.add(X);
                        predator.add(Y);
                        Predators_Location.add(predator);                               //pobierz jego lokalizacje i dodaj do listy.
                    } 
                }
            }
        }
        if(Predators_Location.size()!=0) return Predators_Location;
        return null;
    }
}
