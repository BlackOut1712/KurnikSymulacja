package org.example.app;

public class Hen extends Animal {
    public static final String sign =Map.CYAN+"K"+Map.RESET;
    private int laidEggs = 0;

    public Hen(){
        super();
        Map.set(this.X, this.Y, sign);
        this.setHP(60);
        this.setSpeed(1.33);
        this.setSign(sign);
        this.setVision(5);
    }

    public String getStats(){
        System.out.println(super.getStats()+"\nLaid Eggs: "+laidEggs+".");
        return super.getStats()+"\nLaid Eggs: "+laidEggs+".";
    }

    public boolean layAnEgg(){
        if(Gameplay.getCocks().size()!=0){
            Gameplay.addEgg();
            laidEggs++;
            return true;
        }
        return false;
    }

    public void makeAMove(){
        if( Gameplay.isDay() || !run()){
            double randomizer = Math.random();
            if(randomizer<=0.05 && layAnEgg()){
                if(Gameplay.getLogsSetting()) System.out.println("Kura ("+this.X+","+this.Y+") znosi jajo");
            }
            else{
                move();
            }
        }
    }
}
