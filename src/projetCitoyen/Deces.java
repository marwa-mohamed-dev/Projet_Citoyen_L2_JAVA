package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Deces implements Serializable{
	
	//Attributs de la classe Décès
    public Date dateDeces;

    public Citoyen personne;				//lien avec citoyen, puisque c'est une personne qui décède

    public Mairie mairie;					//lien avec mairie, comme lieu de décès
    
    //Constructeur de la classe Décès
    public Deces(Citoyen x, Date date) {
    	personne = x;
    	dateDeces = date;
    }
    
    public Deces(Citoyen x) {
    	personne = x;
    }
    
    //Méthodes de la classe Décès
    public void setMairie(Mairie m) {		//place la mairie où a eu lieu le décès
    	mairie = m;
    }
    
    public Mairie getMairie() {				//recherche la mairie où a eu lieu le décès
    	return mairie;
    }

}

