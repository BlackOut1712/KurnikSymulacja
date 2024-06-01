package org.example.app;

import java.util.Iterator;

public class Henhouse {
    private static boolean isOverloaded = false;
    private static double disasterLevel;           //Parametr zawierajacy informacje ile kur umiera przy przeludnieniu

    public static void checkIfOverLoaded(){
        int CapacityOfHenhouse = (int) ((Map.getXsize() + Map.getYsize())/4);
        if(Gameplay.getHens().size() + Gameplay.getCocks().size() > CapacityOfHenhouse){
            System.out.println("Och! Kurnik jest przepełniony! Kury toczą wojnę domową. Ginie "+(disasterLevel*100)+" populacji kur.");
            int i=0;
            for(Iterator<Hen> it = Gameplay.getHens().iterator(); it.hasNext() && i< Gameplay.getHens().size()*disasterLevel; i++){
                Gameplay.RemoveAnimal(it);
            }
        }
    }
}
