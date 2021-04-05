package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Mairie implements Serializable{

	//Attributs de la classe Mairie
    public String nomVille;
    
    public int numVille;

    public ArrayList<Citoyen> listCitoyens;			//liste contenant l'int�gralit� des citoyens de la ville

    public ArrayList<Naissance> listNaissances;		//liste contenant l'int�gralit� des citoyens �tant n�s au sein de la ville

    public ArrayList<Mariage> listMariages;			//liste contenant tous les marriages effectu�s au sein de la mairie

    public ArrayList<Deces> listDeces;				//liste contenant l'int�gralit� des habitants de la ville d�c�d�s
    
    //Constructeur de la classe Mairie
    public Mairie(int num,String n) {				//cr�ation d'une nouvelle mairie
    	nomVille = n;								//cr�ation du nom de la ville
    	numVille = num;								//cr�ation du num�ro de la ville (code postal)
    	listCitoyens = new ArrayList<>();			//nouvelle liste de citoyens pour une nouvelle ville
		listMariages = new ArrayList<>();			//nouvelle liste de mariages
		listDeces = new ArrayList<>();				//nouvelle liste de d�c�s
		listNaissances = new ArrayList<>();
    }

}