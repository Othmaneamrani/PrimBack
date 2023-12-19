package ro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {
    public static void main(String[] args) throws FileNotFoundException {
    		List<String> list = new ArrayList<>();
            System.out.println("Entrez le nombre d'aretes :");
            Scanner scanner = new Scanner(System.in);
            int nbr = scanner.nextInt();
            for (int i=0 ; i < nbr ; i++) {
            
            	System.out.println("Arete : " + (i+1));
            	
            	  System.out.println("Entrez le premier sommet :");
                  String sommet1 = scanner.next();
                  System.out.println("Entrez le deuxieme sommet :");
                  String sommet2 = scanner.next();
                  System.out.println("Entrez le poids de l'arete :");
                  int poids = scanner.nextInt(); 
                  String sommetsEtPoids = sommet1 + " " + sommet2 + " " + poids;
                  list.add(sommetsEtPoids);
            }
            PrintStream originalOut = System.out;
            
           System.setOut(new PrintStream(new File("C:\\Users\\user\\OneDrive\\Bureau\\Ro\\DataPrim.txt")));
           for(String sommet : list) {
           System.out.println(sommet);
           }
           System.setOut(originalOut);
           scanner.close();
           System.out.println("OK");


    	
           
    }

}
