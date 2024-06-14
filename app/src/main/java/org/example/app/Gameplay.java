package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;

public class Gameplay {
    private static int TURY;
    private static int DaysTilHatch=3;
    private static int Day = 0;
    private static int MaxDays;
    private static boolean isDay;
    
    private static ArrayList<Hen> kury = new ArrayList<>();
    private static ArrayList<Cock> koguty = new ArrayList<>();
    private static ArrayList<Fox> lisy = new ArrayList<>();
    private static ArrayList<Dog> psy = new ArrayList<>();
    private static ArrayList<Egg> jaja = new ArrayList<>();
    private static ArrayList<ArrayList<? extends Animal>> zwierzeta = new ArrayList<>();
    
    public static void addCock(){
        Cock newbie = new Cock();
        koguty.add(newbie);
    }

    public static void addCock(int HowMany){
        for(int i=0; i<HowMany; i++){
            addCock();
        }
    }

    public static void addHen(){
        Hen newbie = new Hen();
        kury.add(newbie);
    }
    
    public static void addHen(int HowMany){
        for(int i=0; i<HowMany; i++){
            addHen();
        }
    }

    public static void addDog(int HowMany){
        for(int i=0; i<HowMany; i++){
            Dog newbie = new Dog();
            psy.add(newbie);
        }
    }

    public static void addFox(){
        Fox newbie = new Fox();
        lisy.add(newbie);
    }

    public static void addFox(int HowMany){
        for(int i=0; i<HowMany;i++){
            addFox();
        }
    }

    public static void addEgg(){
        Egg newbie = new Egg(DaysTilHatch);
        jaja.add(newbie);
    }
    
    private static void CreateAnimalList(){
        zwierzeta.add(koguty);
        zwierzeta.add(lisy);
        zwierzeta.add(kury);
        zwierzeta.add(psy);
        
    }

    public static int currentDay(){
        return Day;
    }

    private static void checkIfDay(int i){
        if(i>=(int) TURY/2){                                                                                     
            isDay = false;
            for(Fox Lis: getFoxes()){
                Map.set(Lis.X, Lis.Y, Fox.sign);
            }
        }
        else{
            isDay = true;
            for(Fox Lis: getFoxes()){
                Map.set(Lis.X, Lis.Y, Map.DEFAULT_SIGN);
            }
        }
    }

    private static boolean checkSymulationDestination(){
        //Sprawdz czy dalsza symulacja ma sens - nie przekroczono limitu dni, czy żyją lisy oraz koguty wraz z kurami/jajami.
        if(Day<MaxDays && lisy.size() != 0 && ((kury.size() !=0 || jaja.size()!=0) /*&& koguty.size() !=0*/)){
            return true;
        }
        return false;
    }
    
    public static String determineSpecies(Animal zwierze){
        String species = "<<blad>>";
        if(zwierze instanceof Dog) species = "Pies";
        else if(zwierze instanceof Fox) species = "Lis";
        else if(zwierze instanceof Hen) species = "Kura";
        else if(zwierze instanceof Cock) species = "Kogut";

        return species;
    }

    private static void foxReproduction(){
        int newFoxes = (int) Fox.getAnimalsEaten()/5;
        if(newFoxes>0){
            addFox(newFoxes);
            System.out.println("Populacja lisow zwieksza sie. Liczba nowych lisow: "+newFoxes);
            Fox.setAnimalsEaten(Fox.getAnimalsEaten()%5);
        }
    }

    public static ArrayList<ArrayList<? extends Animal>> getAnimals(){
        return zwierzeta;
    }

    public static int getTurnNumber(){
        return TURY;
    }

    public static ArrayList<Hen> getHens(){
        return kury;
    }

    public static ArrayList<Cock> getCocks(){
        return koguty;
    }

    public static ArrayList<Fox> getFoxes(){
        return lisy;
    }

    public static ArrayList<Dog> getDogs(){
        return psy;
    }

    public static ArrayList<Egg> getEggs(){
        return jaja;
    }

    public static boolean isDay(){
        return isDay;
    }

    private static void printTitle(int i){
        checkIfDay(i);
        System.out.println("\n" + ((isDay == true) ? "Dzien " : "Noc ") + Day + " Tura: "+(i+1));
    }

    public static void removeAnimal(Iterator it){
        it.remove();
    }

    private static void randomizeAnimalsPosition(){
        CreateAnimalList();
        for(int i=0; i<100; i++){
            for(ArrayList<? extends Animal> rodzaj: zwierzeta){
                for(Animal zwierze: rodzaj){
                    zwierze.move();
                }
            }
        }
    }

    private static void raport(){
        String raport = "-";

        if(getFoxes().size() == 0){
            raport = "Kury wygrywaja! Populacja lisow wymarla. "; 
        }
        else if(getHens().size() == 0 && getEggs().size() == 0){
            raport = "Lisy wygrywaja! Populacja kur (w tym jaj) wymarla. ";
        }
        else if(Day > MaxDays){
            raport = "Minal wskazany czas symulacji. ";
        }

        raport = raport + "\nStan kurnika: \n"+getHens().size()+" kur \n"+getCocks().size()+" kogutow \n" + getEggs().size() + " jaj \n" + getDogs().size() + " psow. \nPopulacja lisow: " + getFoxes().size();
        System.out.println(raport);
    }

    public static void setDaysLimit(int number){
        MaxDays = number;
    }

    public static void setTurnNumber(int number){
        TURY = number;
    }

    public static void setDaysToHatchAnEgg(int number){
        DaysTilHatch = number;
    }

    public static void startSymulation(){
        randomizeAnimalsPosition();

        while(checkSymulationDestination()){          
            Day++;                                                                                                  //Rozpocznij dzień:
            for(int i=0; i<getTurnNumber(); i++){                                                                   //Przebieg tury:
                printTitle(i);                 
                Henhouse.checkIfOverLoaded();                                                                        //Sprawdz, czy kurnik nie jest przeładowany
                Map.show();                                                                                          //Wyswietl mape

                for(ArrayList<? extends Animal> Animals: zwierzeta){                                                   //Zwierzeta - wykonujcie ruch
                    for(Animal zwierze: Animals){
                        zwierze.makeAMove(); 
                    }
                }
                //Farmer.makeAMove();                                                                                   //Farmer - wykonaj ruch
                
            }
            for(Iterator<Egg> iterator = getEggs().iterator(); iterator.hasNext();){                                    //Jaja - ruch
                Egg jajko = iterator.next();
                jajko.countDaysToHatch(iterator);
            }
            foxReproduction();                                                                                          //Sprawdz, czy populacja lisów się zwiększyła.        
        }

        raport();
    }



}
