package org.example.app;


public class Map {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";


    private static int Ysize, Xsize;
    private static String[][] map;

    public static int GenerateMap(int width, int height){
        if(map != null){
            System.out.println("Map already exists.");
            return -1;
        }
        Xsize = width;
        Ysize = height;
        InitializeMap();
        FulfillMap();
        return 0;
    }


    private static void InitializeMap(){
        map = new String[Ysize][Xsize];
    }

    private static void FulfillMap(){
        for(int y=0; y<Ysize; y++){
            for(int x=0; x<Xsize; x++){
                map[y][x] = "x";
            }
        }
    }

    public static void ShowMap(){
        System.out.print("\n");
        for(int y=0; y<Ysize; y++){
            for(int x=0; x<Xsize; x++){
                System.out.print(map[y][x]);
            }
            System.out.print("\n");
        }
    }

    public static String get(int x, int y){
        if(x<0 || y<0) return "-";
        return map[y][x];
    }

    public static boolean set(int x, int y, String New){
        map[y][x] = New;
        return true;
    }

    public static int getXsize(){
        return Xsize;
    }

    public static int getYsize(){
        return Ysize;
    }

    public static boolean checkIfFree(int x, int y){
        if(x < 0 || y<0 || x >= Map.getXsize() || y >= Map.getYsize() || Map.get(x,y) !="x"){
            return false;
        }
        else return true;
    }
}
