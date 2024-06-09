package org.example.app;

import java.util.Iterator;

public class Farmer {
    private static boolean isAwake = false;
    private static int FoxesKilled = 0;
    private static double ChanceToAwake;

    public static boolean isAwake(){
        return isAwake;
    }

    public static void shoot(){
        int pickedFox = (int) (Math.random() * Gameplay.getFoxes().size());
        int index = 0;
        
        for(Iterator<Fox> it = Gameplay.getFoxes().iterator(); it.hasNext(); index++){
            Fox CurrentFox = it.next();
            if(index == pickedFox){
                CurrentFox.getDamage(999,it);
                System.out.println("Lis ("+CurrentFox.X+","+CurrentFox.Y+") został zastrzelony.");
                FoxesKilled++;
                break;
            }
        }
    }

    public static void MakeAMove(){
        if(Gameplay.isDay() == false && Math.random() < ChanceToAwake){
            System.out.println("Farmer wstał - będzie strzał!");
            shoot();
        }
    }

    public static int FoxesKilled(){
        return FoxesKilled;
    }

    public static void SetChanceToAwake(double chance){
        ChanceToAwake = chance;
    }
}
