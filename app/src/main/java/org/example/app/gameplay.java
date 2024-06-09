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
    
    public static void AddHen(){
        Hen newbie = new Hen();
        kury.add(newbie);
    }
    
    public static void AddHen(int HowMany){
        for(int i=0; i<HowMany; i++){
            AddHen();
        }
    }

    public static void AddDog(int HowMany){
        for(int i=0; i<HowMany; i++){
            Dog newbie = new Dog();
            psy.add(newbie);
        }
    }

    public static void AddFox(){
        Fox newbie = new Fox();
        lisy.add(newbie);
    }

    public static void AddFox(int HowMany){
        for(int i=0; i<HowMany;i++){
            AddFox();
        }
    }

    public static void AddEgg(){
        Egg newbie = new Egg(DaysTilHatch);
        jaja.add(newbie);
    }
    
    private static void CreateAnimalList(){
        zwierzeta.add(kury);
        zwierzeta.add(lisy);
        zwierzeta.add(psy);
        zwierzeta.add(koguty);
    }

    public static int CurrentDay(){
        return Day;
    }

    private static void CheckIfDay(int i){
        if(i>=(int) TURY/2){                                                                                     
            isDay = false;
            for(Fox Lis: getFoxes()){
                Map.set(Lis.X, Lis.Y, Fox.sign);
            }
        }
        else{
            isDay = true;
            for(Fox Lis: getFoxes()){
                Map.set(Lis.X, Lis.Y, "x");
            }
        }
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

    public static void RemoveAnimal(Iterator it){
        it.remove();
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

    public static void StartSymulation(){
        CreateAnimalList();
        while(Day<MaxDays){    
            Day++;                                                                                              //Rozpocznij dzień:
            for(int i=0; i<getTurnNumber(); i++){                                                                   //Przebieg tury:
                CheckIfDay(i);
                System.out.println("\n" + ((isDay == true) ? "Dzien " : "Noc ") + Day + " Tura: "+(i+1));
    
                Henhouse.checkIfOverLoaded();                                                                           //Sprawdz, czy kurnik nie jest przeładowany
                
                Map.ShowMap();                                                                                          //Wyswietl mape (? - przed czy po jajkach?)

                for(ArrayList<? extends Animal> Animals: zwierzeta){                                                    //Zwierzeta - wykonujcie ruch
                    for(Animal zwierze: Animals){
                        zwierze.MakeAMove(); 
                    }
                }
                Farmer.MakeAMove();                                                                                     //Farmer - wykonaj ruch
                
            }
            for(Iterator<Egg> iterator = getEggs().iterator(); iterator.hasNext();){                                    //Jaja - ruch
                Egg jajko = iterator.next();
                jajko.CountDaysToHatch(iterator);
            }
            
        }
    }

}
