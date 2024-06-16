package org.example.app;

public class Hen extends Animal {
    public static final String sign =Map.CYAN+"K"+Map.RESET;
    private int laidEggs = 0;  //Parametr w pozniejszym stadium miał służyć jako wyznacznik MVP wśród Kur, ale nie zdążyłem :(

    public Hen(){
        super();
        Map.set(this.X, this.Y, sign);
        this.setHP(60);
        this.setSpeed(1.33);
        this.setSign(sign);
        this.setVision(5);
    }

    public boolean layAnEgg(){
        /* Funkcja layAnEgg() odpowiada za składanie jaj. Zwraca wartość true gdy kura zniesie jajo, oraz false gdy jest to niemożliwe
         * Jajo można złożyć pod warunkiem, że przy życiu pozostał przynajmniej 1 kogut.
         */
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
