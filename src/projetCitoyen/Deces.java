package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Deces implements Serializable{
	
	//Attributs de la classe D�c�s
    public Date dateDeces;

    public Citoyen personne;				//lien avec citoyen, puisque c'est une personne qui d�c�de

    public Mairie mairie;					//lien avec mairie, comme lieu de d�c�s
    
    //Constructeur de la classe D�c�s
    public Deces(Citoyen x, Date date) {
    	personne = x;
    	dateDeces = date;
    }
    
    public Deces(Citoyen x) {
    	personne = x;
    }
    
    //M�thodes de la classe D�c�s
    public void setMairie(Mairie m) {		//place la mairie o� a eu lieu le d�c�s
    	mairie = m;
    }
    
    public Mairie getMairie() {				//recherche la mairie o� a eu lieu le d�c�s
    	return mairie;
    }

}

