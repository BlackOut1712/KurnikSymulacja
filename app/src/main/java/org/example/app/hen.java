package org.example.app;


public class hen extends Animal {
    private int laidEggs = 0;

    public hen(){
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
        //????
    }
}
