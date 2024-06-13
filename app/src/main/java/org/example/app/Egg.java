package org.example.app;

import java.util.Iterator;

public class Egg{
    private boolean isAlive;
    private int DaysTilHatch;

    public Egg(int Days){
        this.DaysTilHatch = Days;
        this.isAlive = true;
    }

    public void countDaysToHatch(Iterator it){
        if(!isAlive){
            Gameplay.removeAnimal(it);
            return;
        }

        if(DaysTilHatch==0){
            hatch(it);
            return;
        }
        DaysTilHatch--;
    }

    private void hatch(Iterator it){
        Gameplay.removeAnimal(it);
        Gameplay.addHen();
        System.out.println("Jajko wykluwa sie.");
    }
}
