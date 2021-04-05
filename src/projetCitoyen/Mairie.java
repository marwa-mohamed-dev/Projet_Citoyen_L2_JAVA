package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Mairie implements Serializable{

	//Attributs de la classe Mairie
    public String nomVille;
    
    public int numVille;

    public ArrayList<Citoyen> listCitoyens;			//liste contenant l'intégralité des citoyens de la ville

    public ArrayList<Naissance> listNaissances;		//liste contenant l'intégralité des citoyens étant nés au sein de la ville

    public ArrayList<Mariage> listMariages;			//liste contenant tous les marriages effectués au sein de la mairie

    public ArrayList<Deces> listDeces;				//liste contenant l'intégralité des habitants de la ville décédés
    
    //Constructeur de la classe Mairie
    public Mairie(int num,String n) {				//création d'une nouvelle mairie
    	nomVille = n;								//création du nom de la ville
    	numVille = num;								//création du numéro de la ville (code postal)
    	listCitoyens = new ArrayList<>();			//nouvelle liste de citoyens pour une nouvelle ville
		listMariages = new ArrayList<>();			//nouvelle liste de mariages
		listDeces = new ArrayList<>();				//nouvelle liste de décès
		listNaissances = new ArrayList<>();
    }

}