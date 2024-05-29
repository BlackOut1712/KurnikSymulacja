package org.example.app;

import java.util.Iterator;

public class Egg {
    private boolean isAlive;
    private int DaysTilHatch;

    public Egg(int Days){
        this.DaysTilHatch = Days;
        this.isAlive = true;
    }

    public void CountDaysToHatch(Iterator it){
        if(!isAlive){
            gameplay.RemoveAnimal(it);
            return;
        }

        if(DaysTilHatch==0){
            Hatch(it);
        }
        DaysTilHatch--;
    }

    private void Hatch(Iterator it){
        gameplay.RemoveAnimal(it);
        gameplay.AddHen();
        System.out.println("Jajko wykluwa się.");
    }
}
