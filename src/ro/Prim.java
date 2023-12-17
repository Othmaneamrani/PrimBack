package ro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
	//LES ATTRIBUTS DE LA CLASSE PRIM
    public String sommet1;
    public String sommet2;
    public int poids;

    
    
    
    //CONSTRUCTEUR AVEC PARAMETRES
    public Prim(String s1, String s2, int p) {
        this.sommet1 = s1;
        this.sommet2 = s2;
        this.poids = p;
    }
    //CONSTRUCTEUR PAR DEFAUT
    public Prim() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    //METHODE AFFICHER QUI AFFICHE L'ARETE CAD LES SOMMETS ET LE POIDS
	public void afficher() {
        System.out.println("(" + sommet1 + "," + sommet2 + ") : " + poids);
    }
	
	
	
	
	//METHODE PRIM QUI PERMET DE FAIRE LE TRAITEMENT 
	//PREND COMME PARAMETRE LE TABLEAU DES ARETES ET LE NOMBRE DE SOMMETS DISTINCTS ET LE SOMMET DE DEPART
    public void algoPrim(List<Prim> edges, int numVertices ,String startVertex) {
    	//VARAIBLE QUI VA CALCULER LE PLUS COURT CHEMIN 
        int sum = 0;
        //TABLEAU MININUMSPANNINGTREE QUI VA CONTENIR LES ARETES QUI FORMENT LE PLUS COURT CHEMIN
        ArrayList<Prim> minimumSpanningTree = new ArrayList<>();
        //ON STOCKE ICI LES SOMMETS DEJA TRAITES ET QUI NE SONT PAS DOUBLES
        Set<String> processedVertices = new HashSet<>();
        //UNE FILE DE PRIORITE QUI STOCKE LES ARETES ET SERA TRIEE EN FONCTIONS DU POIDS DU PLUS PETIT AU PLUS GRAND
        PriorityQueue<Prim> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.poids));

        
        //UNE BOUCLE TANT QUE LA TAILLE DES SOMMETS TRAITES EST INFERIEURE AU NOMBRE DE SOMMETS
        while (processedVertices.size() < numVertices) {
        	//ON AJOUTE LE SOMMET AU TABLEAU DES SOMMETS TRAITES
            processedVertices.add(startVertex);

            //ON PARCOURT LE TABLEAU DES ARETES ENTREE 
            for (Prim edge : edges) {
            	//SI DANS L'ARETE LE SOMMET1 == STARTVERTEX QUI EST DEJA TRAITE ET DANS LE TABLEAU DES SOMMETS TRAITE LE SOMMET2 N'EXISTE PAS
            	//OU BIEN SI DANS L'ARETE LE SOMMET2 == SOMMET STARTVERTEX QUI EST DEJA TRAITE ET DANS LE TABLEAU DES SOMMETS TRAITE LE SOMMET2 N'EXISTE PAS
                if ((edge.sommet1.equals(startVertex) && !processedVertices.contains(edge.sommet2)) ||
                        (edge.sommet2.equals(startVertex) && !processedVertices.contains(edge.sommet1))) {
                	//ON AJOUTE L'ARETE DANS LA FILE DE PRIORITE
                    priorityQueue.add(edge);
                }
            }
            //SI LA FILE N'EST PAS VIDE
            if (!priorityQueue.isEmpty()) {
            	//ON SUPPRIME L'ARETE QUI SE TROUVE AU DEBUT DE LA FILE ET LE CONTENU SUPPRIME SERA STOCKE DANS MINEDGE
                Prim minEdge = priorityQueue.poll();

                //SI LE SOMMET1 DE MINEDGE N'EXISTE PAS DANS LE TABLEAU DES SOMMETS TRAITE
                //OU BIEN SI LE SOMMET2 DE MINEDGE N'EXISTE PAS DANS LE TABLEAU DES SOMMETS TRAITE
                if (!processedVertices.contains(minEdge.sommet1) || !processedVertices.contains(minEdge.sommet2)) {
                	//ON VA AJOUTER MINEDGE QUI A LE PLUS PETIT POIDS DANS LA TABLE DE L'ARBRE COUVRANT MINIMAL
                    minimumSpanningTree.add(minEdge);
                    //ET LA VARIABLE SOMME VA AJOUTE SON CONTENU PRECEDENT AU POIDS DE L'ARETE DE MINEDGE
                    sum += minEdge.poids;

                    //SI LE SOMMETS1 DE MINEDGE N'EXISTE PAS DANS LE TABLEAU DES SOMMETS TRAITE
                    //ON STOCKE LE SOMMET1 DANS STARTVERTEX
                    if (!processedVertices.contains(minEdge.sommet1)) {
                        startVertex = minEdge.sommet1;
                    } else {
                    	//SINON ON STOCKE LE SOMMET2 DANS STARTVERTEX
                        startVertex = minEdge.sommet2;
                    }
                    //ET AINSI DE SUITE JUSQU'A CE QUE LE NOMBRE DE TABLEAU DES SOMMETS TRAITE SOIT EGALE AU NOMBRE DE SOMMETS
                }
            }
        }
        //AFFICHAGE DE L'ARBRE COUVRANT MINIMAL
        System.out.println("Arbre couvrant minimal :");
        for (Prim edge : minimumSpanningTree) {
            edge.afficher();
        }
        //AFFICHAGE DU TABLEAU DES SOMMETS TRAITE
        System.out.println("Sommet traité :");
        for (String vertex : processedVertices) {
            System.out.println(vertex);
        }
        //AFFICHAGE DU POIDS
        System.out.println("Le poids total est : " + sum);
        //TOUS CES AFFICHAGES SERONT REDIRIGES VERS LE FICHIER DE SORTIE JAVA QUI VA ETRE L'ENTREE DU PROGRAMME PYTHON
        
    }

}