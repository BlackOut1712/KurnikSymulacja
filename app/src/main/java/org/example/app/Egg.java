package org.example.app;

import java.util.Iterator;

public class Egg{
    private int DaysTilHatch;

    public Egg(int Days){
        this.DaysTilHatch = Days;
    }

    public void countDaysToHatch(Iterator it){
        /*  Funkcja countDaysToHatch() odpowiada za odliczanie dni do wyklucia, a po ich minięciu wywołanie funkcji Hatch()
         *  Przyjmuje za parametr Iterator, by przekazać go do funkcji Hatch().
         */
        if(DaysTilHatch==0){
            hatch(it);
            return;
        }
        DaysTilHatch--;
    }

    private void hatch(Iterator it){
        /*  Funkcja hatch() odpowiada za wyklucie jaja. Usuwa jajo z listy jaj (za pomoca przekazanego iteratora)
         *  a nastepnie dodaje nowy obiekt kura.
         */
        Gameplay.removeAnimal(it);
        Gameplay.addHen();
        if(Gameplay.getLogsSetting()) System.out.println("Jajko wykluwa sie.");
    }
}
