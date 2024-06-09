package org.example.app;


class start{
    public static void main(String[] args){

        Map.GenerateMap(30, 15);

        Gameplay.AddDog(5);
        for(int i=0; i<100; i++){
            for(Dog pies: Gameplay.getDogs()){
                pies.move();
            }
        }
        
        Gameplay.AddHen(10);
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
        Gameplay.setDaysLimit(30);
        Gameplay.setTurnNumber(12);
        Henhouse.SetDisasterLevel(0.5);

        Gameplay.StartSymulation();   
        
    }
}