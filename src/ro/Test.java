package ro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<Prim> edges = new ArrayList<>();//UNE LISTE VIDE POUR  STOCKER LES ARETES AVEC LEURS POIDS
        Scanner scanner = new Scanner(System.in);//DONNER A L'UTILISATEUR LA MAIN POUR ENTRER LE CHEMIN VERS LE FICHIER D'ENTREE
        System.out.println("Entrez le chemin du fichier contenant les aretes :");
        String inputFilePath = scanner.next();
        //SAISIE DU CHEMIN D'ENTREE_JAVA.TXT

        try {
            Scanner fileScanner = new Scanner(new File(inputFilePath));
            //LECTEURE DE FICHIER ET EXTRACTION DES SOMMETS AINSI QUE
            //LE POIDS ENTRE LES ARETES
            while (fileScanner.hasNext()){//TANT QU'IL YA UNE ARETE NON LUE
                String s1 = fileScanner.next();//ON STOCKE LE 1ER SOMMET
                String s2 = fileScanner.next();//ON STOCKE LE DEUXIEME SOMMET
                int weight = fileScanner.nextInt();//ON STOCKE LE POIDS
                edges.add(new Prim(s1, s2, weight));//ON INSTANCIE LA CLASSE PRIM ET ON STOCKE L'ARETE AVEC LE POIDS DANS LE TABLEAU DES EDGES
            }
          //ON CALCULE LE NOMBRE DE SOMMETS DISTINCTS DANS LE TABLEAU DES EDGES
            int numVertices = edges.stream()//CONVETION DE LA LISTE DES EDGES EN STREAM D'OBJET
                    .map(edge -> Arrays.asList(edge.sommet1, edge.sommet2))//CHAQUE ARETE EST CONVERTIE EN DEUX SOMMETS
                    .flatMap(Collection::stream)//PERMET DE FUSIONNER TOUTES LES LISTES EN UN SEUL FLUX
                    .collect(Collectors.toSet())//COLLECTER LES SOMMETS DISTINCTS SEULEMENT
                    .size();//RETOURNE LE NOMBRE DE SOMMETS DISTINCTS
            
          //ON DEMANDE ICI LE SOMMETS DE DEPART
          System.out.println("Entrez le sommet de depart :");
          String startVertex = scanner.next();
          scanner.close();

          
            // REDIRIEGR LA SORTIE STANDARD CAD CE QUI VA ETRE AFFICHE DANS LA CONSOLE VERS UN FICHIER
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new File("C:\\Users\\user\\OneDrive\\Bureau\\Ro\\SortiePrim.txt")));
            //APPELLE DE LA METHODE ALGOPRIM QUI CE TROUVE DANS LA CLASSE PRIM
            //QUI PREND COMME PARAMETRE LE TABLEAU DES ARETES AVEC LE POIDS ET LE NOMBRE 
            //DE SOMMETS DISTINCTS
            
            new Prim().algoPrim(edges, numVertices,startVertex);

            
            // RESTAURER LA SORTIE STANDARD
            //APRES LE TRAITEMENT DE LA METHODE ALOGOPRIM TOUT CE QUI SERA AFFICHE VA ETRE REDIRIGE VERS LE FICHIER DE SORTIE
            System.setOut(originalOut);
            //SI LE CHEMIN DONNEE PAR L'UTILISATEUR N'EXISTE PAS 
            //UNE EXCEPTION SERA LANCEE
        } catch (FileNotFoundException e) {
            System.err.println("Fichier inéxistant !");;
        }
    }
}