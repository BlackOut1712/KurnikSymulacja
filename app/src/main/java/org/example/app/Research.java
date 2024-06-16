package org.example.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Research {
    
    /* Klasa ta jest troche chaotyczna, ponieważ powstała w celach "prywatnych" - jedynie do przeprowadzenia badań, dlatego wiele parametrów (np. ścieżek plików) jest za-hardcodowane */


    public static void startResearch(){
        Gameplay.setLogSetting(false);
        Gameplay.setVisualisation(false);
        for(int i=4; i<=4; i++){
            try{
                File Rezultat = new File("Badania/2/Results"+i+".txt");
                FileWriter results = new FileWriter(Rezultat);
                results.write("Winner;CocksNumber;HensNumber;DogsNumber;FoxesNumber;HensEaten;\n");
                try{
                    File file = new File("Badania/2/DaneDoBadan"+i+".txt");
                    Scanner fileReader = new Scanner(file);
                    fileReader.nextLine(); //pominiecie linijki etykiet
                    int nr_parametrow = 1;
                    while(fileReader.hasNextLine()){
                        String symulacja = fileReader.nextLine();
                        String[] parameters = symulacja.split(";");
                        System.out.println("Zestaw nr "+nr_parametrow);
                        results.write("Zestaw parametrów nr "+ nr_parametrow+"\n");
                        double srednia =0;
                        for(int j=0; j<100; j++){
                            loadParameters(parameters);
                            Gameplay.startSymulation();
                            srednia += Fox.getHensEaten();
                            results.write(Gameplay.getWinner()+";");
                            results.write(Integer.toString(Gameplay.getCocks().size())+";");
                            results.write(Integer.toString(Gameplay.getHens().size())+";");
                            results.write(Integer.toString(Gameplay.getDogs().size())+";");
                            results.write(Integer.toString(Gameplay.getFoxes().size())+";");
                            results.write(Fox.getHensEaten()+"\n");
                            Gameplay.reset();
                        }
                        srednia/=100;
                        results.write("Srednia ilosc zjedzonych kur: "+srednia+"\n");
                        nr_parametrow++;
                    }
                    results.close();
                    fileReader.close();
                }
                catch(FileNotFoundException e){
                    System.out.println("Nie znaleziono pliku.");
                    System.exit(-1);
                }
            }catch(IOException e){
                System.out.println("Blad.");
                System.exit(-2);
            }
        }
        System.out.println("Koniec.");
    }

    private static void loadParameters(String[] parameters){
        Map.generateMap(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
        Gameplay.setDaysLimit(Integer.parseInt(parameters[2]));
        Henhouse.setDisasterLevel(Double.parseDouble(parameters[3]));
        Farmer.setChanceToAwake(Double.parseDouble(parameters[4]));
        Gameplay.addCock(Integer.parseInt(parameters[5]));
        Gameplay.addHen(Integer.parseInt(parameters[6]));
        Gameplay.addDog(Integer.parseInt(parameters[7]));
        Gameplay.addFox(Integer.parseInt(parameters[8]));
        Cock.setPushingForce(Integer.parseInt(parameters[9]));
    }

}
