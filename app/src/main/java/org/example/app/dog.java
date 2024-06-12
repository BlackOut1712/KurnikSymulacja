package org.example.app;

public class Dog extends Fox{
    public static final String sign = Map.YELLOW + "D" + Map.RESET;
    private boolean isResting = false;
    private Integer RestTurn = null;

    public Dog(){
        super(40, 100);
        this.setHP(200);
        this.setSign(sign);
        this.setSpeed(1.67);
        this.setVision(20);
    }

    private void Rest(){
        this.setHP(this.HP()+0.3*this.maxHP());
        this.isResting = true;
        this.RestTurn=0;
        RestClock();
    }

    private void RestClock(){
        if(this.RestTurn ==3){
            this.RestTurn = null;
            this.isResting = false;
            return;
        }
        this.RestTurn+=1;
    }

    public void MakeAMove(){
        if(this.isResting){
            RestClock();
        }
        else{
            double randomizer = Math.random();
            if(Gameplay.isDay()){      //Podczas dnia, pies może odpocząć.
                if(this.HP() < 0.3*this.maxHP()){           //Jeśli pies ma mało życia, zwiększona szansa na odpoczynek
                    if(randomizer<=0.6){
                        Rest();
                    }
                    else{
                        move();
                    }
                }
                else{
                    if(randomizer<=0.2){
                        Rest();
                    }
                    else{
                        move();
                    }
                }
            }
            
            else{
                if(!this.Hunt()){
                    move();
                }
            }
        }
    }
}
