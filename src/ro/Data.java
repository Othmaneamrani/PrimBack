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
            System.out.println("Entrez le nombre de sommets :");
            Scanner scanner = new Scanner(System.in);
            int nbr = scanner.nextInt();
            for (int i=0 ; i < nbr ; i++) {
            
            	  System.out.println("Entrez deux sommets puis leurs poids :");
                  
                  String sommet1 = scanner.next();
                  String sommet2 = scanner.next();
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
