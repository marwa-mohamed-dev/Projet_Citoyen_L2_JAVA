package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Naissance implements Serializable{
	
	//Attributs de la classe Naissance
    public Date dateNaissance;
    
    public Mairie mairieDeNaissance;		//garde en mémoire la ville de naissance

    public Citoyen parent1;					//indique le premier parent

    public Citoyen parent2;					//indique le deuxième parent 

    public Citoyen citoyen; 				//enfant
    
    //Constructeurs de la classe Naissance
    public Naissance(Citoyen p, Citoyen m, Date date) { //dans le cas d'une naissance en présence des deux parents
    	parent1 = p;									//associe un citoyen comme étant un des parents
    	parent2 = m;									//associe un citoyen comme étant un des parents
    	dateNaissance = date;							//permet d'entrer la date da naissance de l'enfant
    }
    
    public Naissance(Citoyen p, Date date) {			//dans le cas d'une naissance en présence d'un seul des deux parents 
    	parent1 = p;									//associe un citoyen comme étant le seul parent
    	dateNaissance = date;
    }
    
    public Naissance(Date date) {			//dans le cas d'un ajout de citoyen à une mairie,
    	dateNaissance = date;				//permet de rentrer la date de naissance de celui-ci
    }
    
   //Méthodes de la classe
    public void setMairie(Mairie m) {		//place la mairie en tant que ville de naissance
    	mairieDeNaissance = m;
    }
    
    public Mairie getMairie() {
    	return mairieDeNaissance;			//recherche la mairie dans laquelle le citoyen est né
    }
    public static void enregistrementN(Citoyen z, Citoyen y) {		//vérification du lien créé entre l'enfant et les parents
		if((y.identifiant == z.naissance.parent1.identifiant)|| (y.identifiant == z.naissance.parent2.identifiant) ) {
			if (y.sexe=="f") {		//si le lien s'est fait, alors on genre notre réponse en fonction du sexe du parent
			System.out.println("Le nouveau-né a bien été enregistré dans le livret familial de la mère.");
			} else {
				System.out.println("Le nouveau-né a bien été enregistré dans le livret familial du père.");
			}
		}
    }
   

}