package org.example.app;

import java.util.ArrayList;
import java.util.Iterator;

public class Gameplay {
    private static int TURY = 24;
    private static int DaysTilHatch=3;
    private static int Day = 0;
    private static int MaxDays;
    private static boolean isDay;
    private static String Winner;
    private static boolean Visualisation = true;
    private static boolean Logs = true;
    
    private static ArrayList<Hen> kury = new ArrayList<>();
    private static ArrayList<Cock> koguty = new ArrayList<>();
    private static ArrayList<Fox> lisy = new ArrayList<>();
    private static ArrayList<Dog> psy = new ArrayList<>();
    private static ArrayList<Egg> jaja = new ArrayList<>();
    private static ArrayList<ArrayList<? extends Animal>> zwierzeta = new ArrayList<>();
    
    public static void addCock(){
        /* Funkcja addCock() dodaje elemnt klasy Cock */

        Cock newbie = new Cock();
        koguty.add(newbie);
    }

    public static void addCock(int HowMany){
        for(int i=0; i<HowMany; i++){
            addCock();
        }
    }

    public static void addHen(){
        /* Funkcja addHen() dodaje element klasy Hen */
        Hen newbie = new Hen();
        kury.add(newbie);
    }
    
    public static void addHen(int HowMany){
        for(int i=0; i<HowMany; i++){
            addHen();
        }
    }

    public static void addDog(int HowMany){
        /* Funkcja addDog() dodaje, podana jako "HowMany", liczbe elementow klasy Dog*/
        for(int i=0; i<HowMany; i++){
            Dog newbie = new Dog();
            psy.add(newbie);
        }
    }

    public static void addFox(){
        /* Funkcja addFox() dodaje element klasy Fox */
        Fox newbie = new Fox();
        lisy.add(newbie);
    }

    public static void addFox(int HowMany){
        for(int i=0; i<HowMany;i++){
            addFox();
        }
    }

    public static void addEgg(){
        /* Funkcja addEgg() dodaje elemnt klasy Egg */
        Egg newbie = new Egg(DaysTilHatch);
        jaja.add(newbie);
    }
    
    private static void createAnimalList(){
        /* Funkcja createAnimalList() tworzy listę ze wszystkimi obiektami dziedziczącymi
         * po klasie Animal (w celu ułatwienia wywoływania funkcji MakeAMove() dla każdego z nich).
         * Zawiera także kolejność rodzajów zwierząt wykonujących ruch.
         */
        zwierzeta.add(koguty);
        zwierzeta.add(lisy);
        zwierzeta.add(kury);
        zwierzeta.add(psy);
        
    }

    public static int currentDay(){
        return Day;
    }

    private static void checkIfDay(int i){
        /* Funkcja checkIfDay() sprawdza czy podczas i-tej tury (podawanej jako parametr) trwa dzien czy noc.
         * Jeśli dzień - zabiera z mapy wszystkie lisy, jeśli noc - przywraca je na mape.
         */
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
        /*Funkcja checkSymulationDestination sprawdza czy dalsza symulacja ma sens - nie przekroczono limitu dni, czy żyją lisy oraz kury/jaja.
         * Zwraca true, jeśli dalsza symulacja ma sens, lub false w przeciwnym wypadku.
         */
        if(Day<MaxDays && lisy.size() != 0 && ((kury.size() !=0 || jaja.size()!=0))){
            return true;
        }
        return false;
    }
    
    public static String determineSpecies(Animal zwierze){
        /* Funkcja determineSpecies() określa rodzaj zwierzecia Animal danego jako parametr */
        String species = "<<blad>>";
        if(zwierze instanceof Dog) species = "Pies";
        else if(zwierze instanceof Fox) species = "Lis";
        else if(zwierze instanceof Hen) species = "Kura";
        else if(zwierze instanceof Cock) species = "Kogut";

        return species;
    }

    private static void foxReproduction(){
        /* Funkcja foxReproduction() odpowiada za rozrost populacji lisów. Co piątą kurę, dodaje nowego Lisa*/
        int newFoxes = (int) Fox.getAnimalsEaten()/5;
        if(newFoxes>0){
            addFox(newFoxes);
            if(Logs) System.out.println("Populacja lisow zwieksza sie. Liczba nowych lisow: "+newFoxes);
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

    public static boolean getLogsSetting(){
        return Logs;
    }

    public static String getWinner(){
        return Winner;
    }

    public static boolean getVisualisation(){
        return Visualisation;
    }

    public static boolean isDay(){
        return isDay;
    }

    private static void printTitle(int i){
        /*  funkcja printTitle() jest czescia wizualizacji i odpowiada za wydrukowanie
         *  numeru obecnego dnia i tury (podawanej jako parametr i). Jeśli wizualizacja jest
         *  wyłączona, funkcja nic nie robi.
         */
        if(!Gameplay.getVisualisation()){
            return;
        }
        checkIfDay(i);
        System.out.println("\n" + ((isDay == true) ? "Dzien " : "Noc ") + Day + " Tura: "+(i+1));
    }

    private static void raport(){
        /*  Funkcja raport() odpowiada za podsumowanie symulacji po jej zakończeniu.
         *  Drukuje jej wynik oraz ustala zwycięzce. Pokazuje także końcowy stan kurnika,
         *  ile trwała symulacja oraz ile zwierząt zostało zastrzelonych przez farmera.
         */
        String raport = "Minal wskazany czas symulacji.";
        setWinner("Draw");

        if(getFoxes().size() == 0 && (getHens().size() !=0 || getEggs().size() !=0)){
            raport = "Kury wygrywaja! Populacja lisow wymarla. ";
            setWinner("Henhouse");
        }
        else if(getHens().size() == 0 && getEggs().size() == 0){
            raport = "Lisy wygrywaja! Populacja kur (w tym jaj) wymarla. ";
            setWinner("Foxes");
        }

        raport = raport + "\nStan kurnika: \n"+getHens().size()+" kur \n"+getCocks().size()+" kogutow \n" + getEggs().size() + " jaj \n" + getDogs().size()
                + " psow. \nPopulacja lisow: " + getFoxes().size() +"\nZastrzelone przez farmera: "+Farmer.foxesKilled() +"\nCzas symulacji: "+Day +" dni.";
        
        
        System.out.println(raport);               
        
    }

    private static void regenerateAll(){
        /* Funkcja regenerateAll() jest wywoływana na początku każdego dnia, oraz regeneruje 10% życia każdego ze zwierząt.*/
        for(ArrayList<? extends Animal> rodzaj_zwierzecia: zwierzeta){
            for(Animal zwierze: rodzaj_zwierzecia){
                zwierze.regenerate();
            }
        }
    }

    public static void removeAnimal(Iterator it){
        /* Funkcja removeAnimal() odpowiada za usuwanie martwych zwierząt z symulacji. Przyjmuje iterator za parametr, gdyż
         * zwierzęta są usuwane ze swoich list, co wymaga iterowania po nich i dopiero usuwania.
         */
        it.remove();
    }

    public static void reset(){
        /* Funkcja reset() powstała dla klasy Research oraz ma na celu jedynie ułatwienie badań.
         * Nie ma wpływu na symulacje. Odpowiada jedynie za reset wszystkich postępów poprzedniej symulacji.
         */
        zwierzeta.clear();
        kury.clear();
        koguty.clear();
        lisy.clear();
        psy.clear();
        jaja.clear();
        Farmer.reset();
        Map.reset();
        Fox.reset();

        Day = 0;
    }

    public static void setDaysLimit(int number){
        MaxDays = number;
    }

    public static void setLogSetting(boolean option){
        Logs = option;
    }

    public static void setTurnNumber(int number){
        TURY = number;
    }

    private static void setWinner(String winner){
        Winner = winner;
    }

    public static void setVisualisation(boolean X){
        Visualisation = X;
    }

    public static void startSymulation(){
        /*  Funkcja startSymulation() wprawia w ruch całą symulacje. Odpowiada m.in. za poprawną kolejność wykonywania poszczególnych zadań.*/

        createAnimalList();
        while(checkSymulationDestination()){
            Day++;                                                                                                  //Rozpocznij dzień:   
            if(Day>1){
                regenerateAll();                                                                                    //Gdy nastąpi nowy dzień, ulecz każde zwierzę o 10% HP.
            } 
            Farmer.enableToMove();     
            
            for(int i=0; i<getTurnNumber(); i++){                                                                   //Przebieg tury:                 
                Henhouse.checkIfOverLoaded();                                                                        //Sprawdz, czy kurnik nie jest przeładowany
                printTitle(i);
                Map.show();                                                                                          //Wyswietl mape
                for(ArrayList<? extends Animal> Animals: zwierzeta){                                                   //Zwierzeta - wykonujcie ruch
                    for(Animal zwierze: Animals){
                        zwierze.makeAMove(); 
                    }
                }
                Farmer.makeAMove();                                                                                   //Farmer - wykonaj ruch
                
            }
            for(Iterator<Egg> iterator = getEggs().iterator(); iterator.hasNext();){                                    //Jaja - ruch
                Egg jajko = iterator.next();
                jajko.countDaysToHatch(iterator);
            }
            foxReproduction();                                                                                          //Sprawdz, czy populacja lisów się zwiększyła.       
        }
        Map.show();
        raport();
    }



}
