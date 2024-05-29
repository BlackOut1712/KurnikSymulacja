package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;

public class gameplay {
    private static int TURY;
    private static int DaysTilHatch;
    private static int Day = 0;
    private static int MaxDays;
    private static boolean isDay;
    
    private static ArrayList<hen> kury = new ArrayList();
    private static ArrayList koguty = new ArrayList();
    private static ArrayList<Fox> lisy = new ArrayList();
    private static ArrayList psy = new ArrayList();
    private static ArrayList<Egg> jaja = new ArrayList<>();
    

    public static ArrayList<hen> getHens(){
        return kury;
    }

    public static ArrayList getCocks(){
        return koguty;
    }

    public static ArrayList<Fox> getFoxes(){
        return lisy;
    }

    public static ArrayList getDogs(){
        return psy;
    }

    public static ArrayList<Egg> getEggs(){
        return jaja;
    }
    public static int CurrentDay(){
        return Day;
    }

    public static void AddHen(){
        hen newbie = new hen();
        kury.add(newbie);
    }
    
    public static void AddHen(int HowMany){
        for(int i=0; i<HowMany; i++){
            AddHen();
        }
    }

    public static void RemoveAnimal(Iterator it){
        it.remove();
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


    public static void setTurnNumber(int number){
        TURY = number;
    }

    public static void setDaysToHatchAnEgg(int number){
        DaysTilHatch = number;
    }

    public static int getTurnNumber(){
        return TURY;
    }

    public static void StartSymulation(){
        while(Day<3){
            Day++;
            for(int i=0; i<getTurnNumber(); i++){
                if(i>(int) TURY/2){
                    isDay = false;
                }
                else{
                    isDay = true;
                }
                System.out.println("\n" + ((isDay == true) ? "Dzien " : "Noc ") + Day + " Tura: "+(i+1));
                for(hen kura: getHens()){
                    kura.MakeAMove();
                }
                for(Fox lis: getFoxes()){
                    lis.MakeAMove();
                }
                for(Iterator<Egg> iterator = getEggs().iterator(); iterator.hasNext();){
                    Egg jajko = iterator.next();
                    jajko.CountDaysToHatch(iterator);
                }
                Map.ShowMap();
            }
        }

    }

}
