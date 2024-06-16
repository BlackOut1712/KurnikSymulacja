package org.example.app;

import java.util.Iterator;

public class Farmer {
    private static int FoxesKilled = 0;
    private static double ChanceToAwake;
    private static boolean alreadyMoved = false;

    public static void enableToMove(){
        alreadyMoved = false;
    }

    public static int foxesKilled(){
        return FoxesKilled;
    }
    
    public static void makeAMove(){
        if(alreadyMoved){
            return;
        }
        if(Gameplay.isDay() == false && Math.random() < ChanceToAwake && Gameplay.getFoxes().size() !=0){
            if(Gameplay.getVisualisation()) System.out.println("Farmer wstal - bedzie strzal!");
            shoot();
            alreadyMoved = true;
            /* Farmer może strzelać tylko raz na noc. Po oddaniu strzału, zablokuj jego możliwość wykonania ruchu */
        }
    }

    public static void reset(){
        /*  Funkcja reset() powstała dla klasy Research, by resetowac wszystkie parametry tej klasy po każdej z symulacji, podczas badań. */
        FoxesKilled = 0;
        alreadyMoved = false;
    }

    public static void setChanceToAwake(double chance){
        ChanceToAwake = chance;
    }

    public static void shoot(){
        /*  Funkcja shoot() odpowiada za strzelanie do lisów w nocy. Losuje obiekt klasy Lis z listy wszystkich lisów, a następnie zadaje mu duże obrażenia)*/

        int pickedFox = (int) (Math.random() * Gameplay.getFoxes().size());
        int index = 0;
        
        for(Iterator<Fox> it = Gameplay.getFoxes().iterator(); it.hasNext(); index++){
            Fox CurrentFox = it.next();
            if(index == pickedFox){
                CurrentFox.getDamage(999,it);
                if(Gameplay.getVisualisation()) System.out.println("Lis ("+CurrentFox.X+","+CurrentFox.Y+") zostal zastrzelony.");
                FoxesKilled++;
                break;
            }
        }
    }
}
