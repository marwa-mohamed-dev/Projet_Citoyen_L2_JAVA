package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Mariage implements Serializable{
	
	//Attributs de la classe Marriage
    public Date dateMariage;

    public Divorce divorce;			//lien avec la classe divorce car un mariage peut devenir un divorce

    public Citoyen conjoint1;		//premi�re personnne du futur couple
    
    public Citoyen conjoint2;		//deuxi�me personne du futur couple

    public Mairie mairie;			//lien avec Mairie car c'est une action qui s'y d�roule exclusivement
    
    //Constructeur de la classe Marriage
    public Mariage(Citoyen x, Citoyen y, Date date) {
    	conjoint1 = x;
    	conjoint2 = y;
    	dateMariage = date;
    }

    public void setMairie(Mairie m) {			//place la mairie o� a eu lieu le marriage
    	mairie = m;
    }
    
    public Mairie getMairie() {					//recherche la mairie o� a eu lieu le mariage
    	return mairie;
    }
    
    public void setDivorce(Date date) {
    	divorce = new Divorce(date);		//cr�er le divorce de deux citoyens 
    }
    
    public Divorce getDivorce() {				//r�cup�re les informations du divorce
    	return divorce;
    }
    public static void repriseNomInitial(Citoyen x, Citoyen y) { //apr�s divorce ou d�c�s du conjoint
		if(x.nomUsage == y.nom) {			//on recherche le nom intial en comparant les nom et nom d'usage  
			x.nomUsage=x.nom;
		} else if (y.nomUsage == x.nom) {	//si le nom d'usage de l'un est �gal au nom de l'autre
			y.nomUsage=y.nom;				//alors la personne au nom d'usage reprend son nom 
		}
	}
    
    public static String nomEnfant(Citoyen x, Citoyen y) {	//donne un nom de famille � un enfant pour une naissance
		String nomEnfant;
		int i = x.mariage.size()-1;
		if((y.mariage.isEmpty())||(y.mariage.get(i).conjoint2 != x)){	//si les deux parents ne sont pas mari�s entre eux
			System.out.println("Choix du nom de famille entre celui du p�re ou celui de la m�re :");
			Citoyen nomParent = main.choixNom(x,y);	//on choisit le nom entre les deux qui sera donn� � l'enfant
			nomEnfant = nomParent.nomUsage;
			return nomEnfant;					//on retourne le nom choisi
		}else{									
			nomEnfant = x.nomUsage;				//sinon le nom est automatiquement le nom d'usage d'un des deux parents
			return nomEnfant;
		} 
	}

}