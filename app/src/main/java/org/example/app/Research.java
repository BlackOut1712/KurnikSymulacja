package org.example.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Research {
    
    public static void startResearch(){
        Gameplay.setLogSetting(false);
        Gameplay.setVisualisation(false);
        try{
            FileWriter results = new FileWriter("Results2.txt");
            results.write("Winner;CocksNumber;HensNumber;DogsNumber;FoxesNumber\n");
            try{
                File file = new File("DaneDoBadan2.txt");
                Scanner fileReader = new Scanner(file);
                fileReader.nextLine(); //pominiecie linijki etykiet
                int nr_parametrow = 1;
                while(fileReader.hasNextLine()){
                    String symulacja = fileReader.nextLine();
                    String[] parameters = symulacja.split(";");
                    System.out.println("Zestaw nr "+nr_parametrow);
                    results.write("Zestaw parametrów nr "+ nr_parametrow+"\n");
                    for(int i=0; i<20; i++){
                        loadParameters(parameters);
                        Gameplay.startSymulation();
                        results.write(Gameplay.getWinner()+";");
                        results.write(Integer.toString(Gameplay.getCocks().size())+";");
                        results.write(Integer.toString(Gameplay.getHens().size())+";");
                        results.write(Integer.toString(Gameplay.getDogs().size())+";");
                        results.write(Integer.toString(Gameplay.getFoxes().size())+"\n");
                        Gameplay.reset();
                    }
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
