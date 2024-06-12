package org.example.app;


class start{
    public static void main(String[] args){

        Map.GenerateMap(24, 12);


        Gameplay.AddCock(0);
        Gameplay.AddDog(1);      
        Gameplay.AddHen(0);       
        Gameplay.AddFox(1);

        Gameplay.setDaysLimit(3);
        Gameplay.setTurnNumber(24);
        Henhouse.SetDisasterLevel(0.5);

        Gameplay.StartSymulation();   
        
    }
}