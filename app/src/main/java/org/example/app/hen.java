package org.example.app;


public class Hen extends Animal {
    private int laidEggs = 0;

    public Hen(){
        super();
        this.setHP(60);
        this.setSpeed(1);
        this.setSign('K');
        this.setVision(5);
        Map.set(this.X, this.Y, this.getSign());
    }

    public String getStats(){
        System.out.println(super.getStats()+"\nLaid Eggs: "+laidEggs+".");
        return super.getStats()+"\nLaid Eggs: "+laidEggs+".";
    }

    public void LayAnEgg(){
        Gameplay.AddEgg();
        laidEggs++;
    }

    public void MakeAMove(){
        if(!RUN()){
            double randomizer = Math.random();
            if(randomizer<=0.05){
                LayAnEgg();
                System.out.println("Kura ("+this.X+","+this.Y+") znosi jajo");
            }
            else{
                move();
            }
        }
    }
}
