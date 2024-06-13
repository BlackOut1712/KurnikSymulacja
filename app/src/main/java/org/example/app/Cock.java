package org.example.app;

import java.util.ArrayList;

public class Cock extends Animal{
    public static final String sign = Map.BLUE+"C"+Map.RESET;

    private int pushedFoxes = 0;
    private int pushingForce=2;

    public Cock(){
        super();
        this.setHP(200);
        this.setSpeed(1.4);
        this.setSign(sign);
        this.setVision(4);
    }

    public int getPushedFoxes(){
        return pushedFoxes;
    }

    public void makeAMove(){
        if(!push()){
            if(!run()){
                move();
            }
        }
    }

    private boolean push(){
        ArrayList<ArrayList<Integer>> Foxes_to_push = checkSurroudings(1);              //Sprawdzenie czy jakiś lis znajduje się na polu obok
        if(Foxes_to_push == null || Foxes_to_push.size()==0){
            //System.out.println("Nie ma kogo odpychac");
            return false;
        }

        int random_index = (int)(Math.random()*Foxes_to_push.size());
        ArrayList<Integer> Lis = Foxes_to_push.get(random_index);                               //Pobranie współrzędnych losowego Lisa znajdujacego sie obok
        int X_lisa = Lis.get(0);
        int Y_lisa = Lis.get(1);

        for(Fox SzukanyLis: Gameplay.getFoxes()){
            if(SzukanyLis.X == X_lisa && SzukanyLis.Y == Y_lisa){           
                int i= pushingForce;

                while(i>0){
                    int Nowy_X= X_lisa, Nowy_Y= Y_lisa;
                    if(this.X-X_lisa > 0){                                                       //Lis znajduje się z lewej
                        Nowy_X =  X_lisa-i;
                    }
                    else if(this.X-X_lisa<0){                                                    //Lis znajduje sie z prawej
                        Nowy_X = X_lisa+i;
                    }
                    else if(this.Y-Y_lisa>0){                                                    //Lis znajduje się na gorze
                        Nowy_Y = Y_lisa-i;
                    }
                    else if(this.Y-Y_lisa<0){                                                   //Lis znajduje sie na dole
                        Nowy_Y = Y_lisa+i;
                    }

                    if(Map.checkIfFree(Nowy_X, Nowy_Y)){                        //Jeśli można odepchnąć lisa (i nie wyleci poza mapę) to to zrób, jeśli nie, zmniejsz siłę pchnięcia o 1
                        Map.set(SzukanyLis.X, SzukanyLis.Y, Map.DEFAULT_SIGN);
                        SzukanyLis.X = Nowy_X;
                        SzukanyLis.Y = Nowy_Y;
                        Map.set(SzukanyLis.X, SzukanyLis.Y, Fox.sign);          //Zastapienie znaku
                        SzukanyLis.stun();                                      //Ogłuszenie lisa.
                        System.out.println("Kogut ("+this.X+","+this.Y+") odpycha lisa z pola("+X_lisa+","+Y_lisa+") na pole ("+SzukanyLis.X+","+SzukanyLis.Y+").");
                        pushedFoxes++;
                        break;
                    }
                    i--;
                }
                break;
            }
        }       
        return true;
    }
}
