package org.example.app;


class start{
    public static void main(String[] args){
        int Number_of_hens = 2;
        int Number_of_foxes = 5;
        int TURY=10;

        Map.GenerateMap(50, 10);


        hen[] kury = new hen[Number_of_hens];
        
        for(int i=0; i<Number_of_hens; i++){
            kury[i] = new hen();
        }
        //Ustaw kury
        for(int i=0; i<1000; i++){
            for(hen kura: kury){
                kura.move();
            }
        }
        
        /*Fox[] lisy = new Fox[Number_of_foxes];
        for(int i=0; i<Number_of_foxes; i++){
            lisy[i] = new Fox();
        }
        //Ustaw lisy
        for(int i=0; i<1000; i++){
            for(Fox lis: lisy){
                lis.move();
            }
        } */

        for(int i=0; i<TURY; i++){
            System.out.println("\n TURA: " + (i+1));
            Map.ShowMap();
            for(hen kura: kury){
                kura.RUN(kura.checkSurroudings());
            }
            /*for(Fox lis: lisy){
                lis.move();
            } */
            
        } 


        

    }
}