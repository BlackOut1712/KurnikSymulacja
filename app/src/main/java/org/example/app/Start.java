package org.example.app;

import java.util.Scanner;

class start{
    public static void main(String[] args){
        //Research.startResearch();

        menu();
        Gameplay.startSymulation();
    }

    public static void menu(){
        /*  Funkcja menu() wyświetla menu do uzupełnienia parametrów startowych symulacji*/
        Scanner in = new Scanner(System.in);
        System.out.println("Witaj w symulacji kurnika! By rozpoczac, wprowadz startowe parametry: \nWielkosc mapy (parametry oddziel litera x): ");
        String[] Wymiary = in.nextLine().split("x");
        Map.generateMap(Integer.parseInt(Wymiary[0]), Integer.parseInt(Wymiary[1])); 
        System.out.println("Maksymalna liczbe dni symulacji: ");
        Gameplay.setDaysLimit(in.nextInt());
        System.out.println("Liczbe kur: ");
        Gameplay.addHen(in.nextInt());
        System.out.println("Liczbe kogutow: ");
        Gameplay.addCock(in.nextInt());
        System.out.println("Liczbe lisow: ");
        Gameplay.addFox(in.nextInt());
        System.out.println("Liczbe psow: ");
        Gameplay.addDog(in.nextInt());
        System.out.println("Szanse na obudzenie farmera (zalecana nie wieksza niz 0,01): ");
        Farmer.setChanceToAwake(in.nextDouble());
        System.out.println("Poziom smiertelnosci w przypadku wojny domowej (np. 0,50): ");
        Henhouse.setDisasterLevel(in.nextDouble());
        System.out.println("Sile kogutow (liczba pol o jakie moze odepchnac drapieznika): ");
        Cock.setPushingForce(in.nextInt());
        /*System.out.println("Czy chcesz wlaczyc wizualizacje? [Y/N]");
        if(in.nextLine().equalsIgnoreCase("N")){
            Gameplay.setVisualisation(false);
        }*/
        in.close();   
    }
}