package org.example.app;

import java.util.ConcurrentModificationException;

class start{
    public static void main(String[] args) throws ConcurrentModificationException{

        Map.GenerateMap(30, 15);

        gameplay.AddHen(4);


        //Ustaw kury
        for(int i=0; i<100; i++){
            for(hen kura: gameplay.getHens()){
                kura.move();
            }
        }
        
        gameplay.AddFox(4);

        //Ustaw lisy
        for(int i=0; i<10; i++){
            for(Fox lis: gameplay.getFoxes()){
                lis.move();
            }
        }
        gameplay.setTurnNumber(4);

        gameplay.StartSymulation();

        /*Map.ShowMap();
        for(int i=0; i<gameplay.getTurnNumber(); i++){
            for(hen kura: gameplay.getHens()){
                kura.MakeAMove();
            }
            for(Fox lis: gameplay.getFoxes()){
                lis.MakeAMove();
            } 
            System.out.println("\n TURA: " + (i+1));
            Map.ShowMap();
        } */


        
        

        
        

    }
}