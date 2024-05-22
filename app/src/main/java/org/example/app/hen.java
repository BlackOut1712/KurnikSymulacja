package org.example.app;

import java.util.ArrayList;
import java.util.Arrays;

public class hen extends Animal {
    private int laidEggs = 0;

    public hen(){
        super();
        this.setHP(60);
        this.setSpeed(1);
        this.setSign('K');
        this.setVision(5);
        Map.set(this.X, this.Y, this.getSign());
    }

    public String getStats(){
        System.out.println(super.getStats()+"\nLaid Eggs: "+laidEggs+".");
        return super.getStats()+"\nLaid Eggs: "+laidEggs+".";
    }

    
    public void RUN(ArrayList<ArrayList<Integer>> Surrouding){
        /* Dzielimy pole widzenia kury na 4 równe sektory (tworzymy układ współrzędnych o środku (0,0) w miejscu kury, a następnie dzielimy go prostymi y=x i y=-x):
               ____
             /\ 1 /\
            |4  K  2|
             \/_3_\/

        Sprawdzamy w jakich "ćwiartkach" (1,2,3,4) tego układu znajdują się lisy*. Na podstawie tego,
        określamy poziom zagrożenia poruszenia się w danym kierunku (np. w lewo). Kura wybiera poruszenie 
        się w kierunku o najniższym poziomie zagrożenia.

        Poziom zagrozenia = liczba lisów w polu widzenia z którymi kura nie zwiększy dystansu 
                            dzielone na dystans między kurą a najbliższym lisem w tej ćwiartce.


        *-jeśli lis znajduje się na granicy obu ćwiartek (kąty pi/4, 3pi/4, -pi/4, -3pi/4), zaliczany jest do obu ćwiartek */


        //Sprawdzamy czy w otoczeniu są drapieżniki
        if(Surrouding == null || Surrouding.size() == 0){
            System.out.println("Nie ma przed czym uciekac.");
            return;
        }


        //Sprawdzamy ilosc drapieznikow w kazdej z cwiartek układu:
        double Left=0, Right=0, Forward=0, Back=0;
        double distanceL=0, distanceR=0, distanceF=0, distanceB=0;
        for(ArrayList<Integer> Predator: Surrouding){
            int X = Predator.get(0);
            int Y = Predator.get(1);

            //------------- Przeskalowanie współrzędnych, zeby kura znajdowala się w środku układu -------------------------------------
            Y -= this.Y;
            X -= this.X;
            double tangens = (double)Y/X;         
            double angle = Math.atan(tangens);
            double currenctDistance = Math.sqrt( X*X+Y*Y);

            // ---------------------- Podział na ćwiartki: ----------------------------------------------------------------

            //Ćwiartka 1 (ruch do przodu)
            if((angle >= Math.PI/4 || angle <= -Math.PI/4 || X==0) && Y>0){      //tangens ma okresowość PI, a arctan zwraca od -pi/2 do pi/2, więc warunki się komplikują. Oprócz warunku na konkretny kąt, muszę sprawdzić znak którejś z współrzędnych.
                Forward++;

                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceF ==0 || currenctDistance < distanceF){
                    distanceF = currenctDistance;
                }
            }

            //Ćwiartka 2 (ruch w prawo)
            else if(angle <= Math.PI/4 && angle >= -Math.PI/4 && X>0){
                Right++;
                                    
                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceR ==0 || currenctDistance < distanceR){
                distanceR = currenctDistance;
                }
            }

            //Ćwiartka 3 (ruch w tył)
            else if((angle >= Math.PI/4 || angle <= -Math.PI/4 || X==0) && Y<0){
                Back++;

                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceB ==0 || currenctDistance < distanceB){
                    distanceB = currenctDistance;
                }
            }
            //Ćwiartka 4 (ruch w lewo)
            else if(angle <= Math.PI/4 && angle >= -Math.PI/4 && X<0){
                Left++;

                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceL ==0 || currenctDistance < distanceL){
                    distanceL = currenctDistance;
                }
            }

        }

        // --------- Obliczanie poziomu zagrozenia -------------
        Forward = ((Forward > 0) ? Forward/distanceF: 0.0);
        Right = ((Right>0) ? Right/distanceR: 0.0);
        Back = ((Back>0) ? Back/distanceB: 0.0);
        Left = ((Left>0) ? Left/distanceL: 0.0);

        // ------------- Podjecie decyzji --------------------

        double[] decision = {Forward, Right, Back, Left};
        Arrays.sort(decision);
        int n = 0;
        
        
        /*double min1 = Math.min(Forward, Back);
        double min2 = Math.min(Right, Left);
        double safestPath = Math.min(min1, min2);*/

        do{
            double safestPath = decision[n];
            if(safestPath == Forward && moveForward()){ 
                break;  
            }
            else if(safestPath == Back && moveBack()){
                break;
            }
            else if(safestPath == Right && moveRight()){  
                break;
            }
            else if(safestPath == Left && moveLeft()){
                break;    
            }
            else{
                n++;
            }
        }while(n < decision.length);

    }


    public void LayAnEgg(){
        //????
    }
}
