package org.example.app;


class start{
    public static void main(String[] args){

        Map.GenerateMap(24, 12);


        Gameplay.AddCock(1);
        //Ustaw Koguty       
        for(int i=0; i<100; i++){
            for(Cock kogut: Gameplay.getCocks()){
                kogut.move();
            }
        } 

        Gameplay.AddDog(2);
        //Ustaw psy
        for(int i=0; i<100; i++){
            for(Dog pies: Gameplay.getDogs()){
                pies.move();
            }
        }
        
        Gameplay.AddHen(6);
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
        Gameplay.setDaysLimit(5);
        Gameplay.setTurnNumber(12);
        Henhouse.SetDisasterLevel(0.5);

        Gameplay.StartSymulation();   
        
    }
}