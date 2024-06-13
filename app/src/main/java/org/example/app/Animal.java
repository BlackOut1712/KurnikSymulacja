package org.example.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public abstract class Animal {
    private String sign;
    private int visual_field;
    public int X=2;
    public int Y=2;
    private double maxHP;
    private double HP;
    private double speed;
    private boolean isAlive;

    public Animal(){
        this.isAlive = true;
    }

    private void checkIfDead(Iterator it){
        if(this.HP<=0){
            this.HP = 0;
            this.isAlive = false;
            Gameplay.removeAnimal(it);
            Map.set(this.X, this.Y, Map.DEFAULT_SIGN);
        }
    }

    public ArrayList<ArrayList<Integer>> checkSurroudings(){
        return checkSurroudings(this.visual_field);
    }

    public ArrayList<ArrayList<Integer>> checkSurroudings(int area){

        String AnimalToSeek = "\0", AnimalToSeek2 = "\0";
        if(this instanceof Hen || this instanceof Dog || (this instanceof Cock && area == 1)) AnimalToSeek = Fox.sign;
        else if((this instanceof Fox && ((Fox) this).isAttacked()==false) || (this instanceof Cock && area == this.visual_field)) AnimalToSeek = Hen.sign;
        else if(this instanceof Fox && ((Fox) this).isAttacked()) AnimalToSeek = Dog.sign;
        
        if(this instanceof Fox && !(this instanceof Dog) && ((Fox)this).isAttacked() == false) AnimalToSeek2 = Cock.sign;

        ArrayList<ArrayList<Integer>> Predators_Location = new ArrayList<>();

        //Sprawdzamy kwadrat od [X(lub Y)-polewidzenia] do [X(lub Y)+polewidzenia] i sprawdzamy czy dane pole nalezy do okręgu pola widzenia.
        // Y jest 0 (dla this.Y-visual_field < 0) lub this.Y-visual_field (dla this.Y-visual_field>=0), kontynuuj do pozycji This.Y+visual_field lub konca tablicy, to samo dla X
        for(int Y = Math.max(this.Y-area, 0); Y<=Math.min(this.Y+area,Map.getYsize()-1); Y++){               
            for(int X= Math.max(this.X-area, 0); X<= Math.min(this.X+area, Map.getXsize()-1); X++){                              
                double distance = Math.sqrt( Math.pow(X-this.X,2) + Math.pow(Y-this.Y,2));          //Policz odległość Euklidesową
                if(distance<=area){                                                         
                    if(Map.get(X,Y) == AnimalToSeek || Map.get(X,Y) == AnimalToSeek2){                                              //Jeśli w polu widzenia znajduje się lis,
                        ArrayList<Integer> predator = new ArrayList<>();
                        predator.add(X);
                        predator.add(Y);
                        Predators_Location.add(predator);                               //pobierz jego lokalizacje i dodaj do listy.
                    } 
                }
            }
        }
        if(Predators_Location.size() == 0){
            return null;
        }
        return Predators_Location;
    }

    public String getSign(){
        return this.sign;
    }

    public double getDamage(double damage, Iterator it){
        this.HP -= damage;
        checkIfDead(it);
        return this.HP;
    }
    
    public String getStats(){
        return 
        "\nRaport"+
        "\nHP: "+this.HP+"/"+this.maxHP
        +".\nSpeed: "+this.speed
        +".\nStatus: " + ((this.isAlive) ? "ALIVE" : "DEAD")
        +".\nPosition: ("+this.X+","+this.Y+").";
                
    }

    public double getSpeed(){
        return this.speed;
    }

    public double HP(){
        return this.HP;
    }

    public int move(){
        if(!isAlive){
            System.out.println("Corpses don't walk... usually...");
            return -1;
        }
        
        for(double i = this.getSpeed(); i>0; i--){    
            if(Math.random()<=i){
                if(!(Map.checkIfFree(X-1, Y) || Map.checkIfFree(X+1, Y) || Map.checkIfFree(X, Y-1) || Map.checkIfFree(X, Y+1))){
                    return -2;
                }

                while(true){
                    double direction = Math.random();

                    if(direction>=0 && direction< 0.25){
                        if(moveLeft()){
                            break;
                        }
                    }

                    else if(direction >= 0.25 && direction < 0.50){
                        if(moveRight()){
                            break;
                        }
                    }

                    else if(direction >= 0.5 && direction < 0.75){
                        if(moveForward()){
                            break;
                        }
                    }

                    else{
                        if(moveBack()){
                            break;
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean moveLeft(){
        if(!isAlive) return false;
        if(Map.checkIfFree(this.X-1, this.Y)){               //check if spot is free
            //System.out.println("Zwierze "+this.getSign() + " ("+this.X+","+this.Y+") rusza sie w Lewo.");
            Map.set(this.X, this.Y, Map.DEFAULT_SIGN);
            this.X-=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    private boolean moveRight(){
        if(!isAlive) return false;
        if(Map.checkIfFree(this.X+1, this.Y)){       //check if spot is free
            //System.out.println("Zwierze "+this.getSign() + " ("+this.X+","+this.Y+") rusza sie w Prawo.");
            Map.set(this.X, this.Y, Map.DEFAULT_SIGN);
            this.X +=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    private boolean moveBack(){
        if(!isAlive) return false;
        if(Map.checkIfFree(this.X, this.Y+1)){       //check if spot is free
            //System.out.println("Zwierze "+this.getSign() + " ("+this.X+","+this.Y+") rusza sie w tył.");
            Map.set(this.X, this.Y, Map.DEFAULT_SIGN);
            this.Y+=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }

    private boolean moveForward(){
        if(!isAlive) return false;
        if(Map.checkIfFree(this.X, this.Y-1)){           //check if spot is free
            //System.out.println("Zwierze "+this.getSign() + " ("+this.X+","+this.Y+") rusza sie w przód.");
            Map.set(this.X, this.Y, Map.DEFAULT_SIGN);
            this.Y-=1;
            Map.set(this.X, this.Y, sign);
            return true;
        }
        return false;
    }
    
    public double maxHP(){
        return this.maxHP;
    }

    public void makeAMove(){}

    public void setHP(double HP){
        if(!isAlive){
            System.out.println("Cannot set HP to the dead animal.");
            return;
        }
        this.maxHP=HP;
        this.HP = HP;
    }

    public void setSign(String sign){
        this.sign = sign;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setVision(int x){
        this.visual_field = x;
    }
    
    public boolean run(){
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

        ArrayList<ArrayList<Integer>> Surrouding = checkSurroudings();
        //Sprawdzamy czy w otoczeniu są drapieżniki
        if(Surrouding == null || Surrouding.size() == 0){
            if(this instanceof Hen) System.out.println("Kura ("+this.X+","+this.Y+") nie ma przed czym uciekac.");
            else if(this instanceof Dog) System.out.println("Pies ("+this.X+","+this.Y+") nie widzi zadnego zagrozenia");
            else if(this instanceof Fox ) System.out.println("Lis ("+this.X+","+this.Y+") nie ma na co polowac.");
            else if(this instanceof Cock) System.out.println("Kogut ("+this.X+","+this.Y+") nie widzi zadnej z kur.");
            return false;
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
            if((angle >= Math.PI/4 || angle <= -Math.PI/4 || X==0) && Y<0){      //tangens ma okresowość PI, a arctan zwraca od -pi/2 do pi/2, więc warunki się komplikują. Oprócz warunku na konkretny kąt, muszę sprawdzić znak którejś z współrzędnych.
                Forward++;

                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceF ==0 || currenctDistance < distanceF){
                    distanceF = currenctDistance;
                }
            }

            //Ćwiartka 2 (ruch w prawo)
            if(angle <= Math.PI/4 && angle >= -Math.PI/4 && X>0){
                Right++;
                                    
                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceR ==0 || currenctDistance < distanceR){
                distanceR = currenctDistance;
                }
            }

            //Ćwiartka 3 (ruch w tył)
            if((angle >= Math.PI/4 || angle <= -Math.PI/4 || X==0) && Y>0){
                Back++;

                //dystans miedzy kura a najblizszym lisem w tej cwiartce:
                if(distanceB ==0 || currenctDistance < distanceB){
                    distanceB = currenctDistance;
                }
            }
            //Ćwiartka 4 (ruch w lewo)
            if(angle <= Math.PI/4 && angle >= -Math.PI/4 && X<0){
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

        //System.out.println("Poziom zagrozenia: \n F: "+Forward+" \n R: "+Right + "\n B: "+Back+ "\n L: "+Left);

        // ------------- Podjecie decyzji --------------------

        double[] decision = {Forward, Right, Back, Left};
        Arrays.sort(decision);

        int n=-1;
        if(this instanceof Hen || this instanceof Cock){
            n=0;
        }
        else if(this instanceof Fox || this instanceof Dog){
            n= decision.length-1;
        }

        // Randomizer sprawia, że n zwiększa się nadwyraz, i kura głupieje

        do{
            double safestPath = decision[n];
            //double randomizer = Math.random();                  // WPROWADZIC RANDOMIZER, ZEBY KURA WYBIERALA LOSOWO GDY KILKA SCIEZEK JEST WOLNYCH
            if(safestPath == Forward  && moveForward()){ 
                break;  
            }
            else if(safestPath == Back && moveBack()){
                break;
            }
            else if(safestPath == Right && moveRight()){  
                break;
            }
            else if(safestPath == Left && moveLeft() ){
                break;    
            }
            else{
                if(this instanceof Hen || this instanceof Cock){
                    n++;
                }
                else if(this instanceof Fox || this instanceof Dog){
                    n--;
                }
            }
        }while(n < decision.length && n>=0);

        return true;
    }

}

