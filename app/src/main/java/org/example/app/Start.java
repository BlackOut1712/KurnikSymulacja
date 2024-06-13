package org.example.app;


class start{
    public static void main(String[] args){

        Map.generateMap(24, 12);


        Gameplay.addCock(3);
        Gameplay.addDog(2);      
        Gameplay.addHen(8);       
        Gameplay.addFox(8);

        Gameplay.setDaysLimit(10);
        Gameplay.setTurnNumber(24);
        Henhouse.setDisasterLevel(0.5);

        Gameplay.startSymulation();   
        
    }
}