package org.example.app;

import java.util.Iterator;

public class Henhouse {
    private static double disasterLevel;           //Parametr zawierajacy informacje ile kur umiera przy przeludnieniu
    private static int capacityOfHenhouse;

    public static void checkIfOverLoaded(){
        if(Gameplay.getHens().size() + Gameplay.getCocks().size() > capacityOfHenhouse){
            System.out.println("Och! Kurnik jest przepelniony! Kury tocze wojne domową. Ginie "+(disasterLevel*100)+"% populacji kur.");
            int i=0;
            for(Iterator<Hen> it = Gameplay.getHens().iterator(); it.hasNext() && i< Gameplay.getHens().size()*disasterLevel; i++){
                Hen kura = it.next();
                kura.getDamage(999, it);
            }
        }
    }

    public static void setCapacity(int number){
        capacityOfHenhouse = number;
    }

    public static void setDisasterLevel(double disaster){
        disasterLevel = disaster;
    }
}
