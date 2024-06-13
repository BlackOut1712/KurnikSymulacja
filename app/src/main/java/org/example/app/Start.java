package org.example.app;


class start{
    public static void main(String[] args){

        Map.generateMap(24, 12);


        Gameplay.addCock(0);
        Gameplay.addDog(1);      
        Gameplay.addHen(0);       
        Gameplay.addFox(1);

        Gameplay.setDaysLimit(3);
        Gameplay.setTurnNumber(24);
        Henhouse.setDisasterLevel(0.5);

        Gameplay.startSymulation();   
        
    }
}