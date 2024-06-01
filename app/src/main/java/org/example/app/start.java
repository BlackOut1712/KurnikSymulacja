package org.example.app;


class start{
    public static void main(String[] args){

        Map.GenerateMap(30, 15);

        Gameplay.AddDog(2);

        for(int i=0; i<100; i++){
            for(Dog pies: Gameplay.getDogs()){
                pies.move();
            }
        }
        
        Gameplay.AddHen(4);
        
        //Ustaw kury
        for(int i=0; i<100; i++){
            for(Hen kura: Gameplay.getHens()){
                kura.move();
            }
        }
        
        Gameplay.AddFox(4);

        //Ustaw lisy
        for(int i=0; i<10; i++){
            for(Fox lis: Gameplay.getFoxes()){
                lis.move();
            }
        }
        Gameplay.setTurnNumber(4);

        Gameplay.StartSymulation();

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