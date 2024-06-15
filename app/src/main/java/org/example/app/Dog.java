package org.example.app;

public class Dog extends Fox{
    public static final String sign = Map.YELLOW + "D" + Map.RESET;
    private boolean isResting = false;
    private Integer restTurn = null;

    public Dog(){
        super(40, 100);
        Map.set(this.X, this.Y, sign);
        this.setHP(120);
        this.setSign(sign);
        this.setSpeed(1.67);
        this.setVision(8);
    }

    private void rest(){
        this.setHP(this.HP()+0.3*this.maxHP());
        this.isResting = true;
        this.restTurn=0;
        restClock();
    }

    private void restClock(){
        if(this.restTurn ==3){
            this.restTurn = null;
            this.isResting = false;
            return;
        }
        this.restTurn+=1;
    }

    public void makeAMove(){
        if(this.isResting){
            restClock();
            return;
        }
        double randomizer = Math.random();
        double chanceToRest = 0.2;
        if(this.HP() < 0.3*this.maxHP()){           //Jeśli pies ma mało życia, zwiększona szansa na odpoczynek
            chanceToRest = 0.6;
        }

        if(Gameplay.isDay()){      
            if(randomizer<=chanceToRest){
                rest();
            }
            else{
                move();
            }    
        }           
        else{
            if(!this.Hunt()){
                move();
            }
        }
    }
}

