package org.example.app;


public class Map {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";
    public static final String DEFAULT_SIGN = "-";


    private static int Ysize, Xsize;
    private static String[][] map;

    public static boolean checkIfFree(int x, int y){
        /* Funkcja checkIfFree() sprawdza, czy dane pole, którego współrzędne są podawane jako parametr, jest wolne.
        Pole jest wolne gdy jest oznaczone znakiem domyślnym. 
        Zwraca true, jeśli pole jest wolne, oraz false w przeciwnym wypadku lub gdy podane pole jest poza mapą */

        if(x < 0 || y<0 || x >= Map.getXsize() || y >= Map.getYsize() || Map.get(x,y) != DEFAULT_SIGN){
            return false;
        }
        return true;
    }

    private static void fulfillMap(){
        /* Funkcja fulfillMap() odpowiada za wypełnienie tablicy dwuwymiarowej (mapy) znakiem domyślnym, by była widoczna w trakcie wizualizacji
         * i umożliwić poruszanie się po niej.
         */
        for(int y=0; y<Ysize; y++){
            for(int x=0; x<Xsize; x++){
                map[y][x] = DEFAULT_SIGN;
            }
        }
    }

    public static int generateMap(int width, int height){
        /*  Funkcja generateMap() odpowiada za generalne stworzenie mapy, składając 
        funkcję initializeMap(), fulfillMap() oraz pobierając parametry wymiarow mapy. 
        */
        if(map != null){
            System.out.println("Map already exists.");
            return -1;
        }
        Xsize = width;
        Ysize = height;
        initializeMap();
        fulfillMap();
        return 0;
    }

    public static String get(int x, int y){
        /* Funkcja get() zwraca wartość pola, którego współrzędne są podawane jako parametr,
         * chyba że pole znajduje się poza mapą.
         */
        if(x<0 || y<0) return "-";
        return map[y][x];
    }

    private static void initializeMap(){
        /* Funkcja initializeMap() odpowiada za inicjalizacje mapy w postaci tablicy dwuwymiarowej o podanych wymiarach
         * oraz określenie jej pojemności dla klasy Henhouse. */
        map = new String[Ysize][Xsize];
        Henhouse.setCapacity((int) ((Xsize*Ysize)/4));
    }

    public static int getXsize(){
        return Xsize;
    }

    public static int getYsize(){
        return Ysize;
    }

    public static void reset(){
        /* Funkcja powstała na potrzeby badań (klasy Research), brak udziału w symulacji */
        map = null;
    }

    public static void set(int x, int y, String New){
        /* Funkcja set() nadpisuje pole, którego współrzędne podane są jako parametr, nowym napisem "New" */
        map[y][x] = New;
    }

    public static void show(){
        /* Funkcja show() odpowiada za wyświetlenie całej mapy, o ile jest włączona wizualizacja. */
        if(!Gameplay.getVisualisation()){
            return;
        }
        System.out.print("\n");
        for(int y=0; y<Ysize; y++){
            for(int x=0; x<Xsize; x++){
                System.out.print(map[y][x]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
