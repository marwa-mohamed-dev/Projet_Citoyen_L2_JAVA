package projetCitoyen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Citoyen implements Serializable{
	
	//Attributs de la classe Citoyen
    public int identifiant;					//num�ro d'identifiant national  

    public String nom;						//nom originel de la personne / nom de jeune fille

    public String nomUsage;					// nom d'usage si mariage

    public String prenom;

    public String sexe;

    public Mairie mairieDeResidence;		//mairie associ�e en fonction du lieu de r�sidence du citoyen

    public ArrayList<Naissance> enfant;		//liste des enfants du citoyen

    public Deces mort ;						//lien avec d�c�s si le citoyen meurt

    public Naissance naissance;				//lien avec naissance 

    public ArrayList<Mariage> mariage;		//liste des mariages successifs du citoyen
    
    //Constructeurs de la classe
    public Citoyen(int id, String n, String p){
    	identifiant = id;
    	nom = n;
    	nomUsage = n;
    	prenom = p;
    	enfant = new ArrayList<Naissance>();	//on donne � chaque citoyen une nouvelle liste d'enfants
    	mariage = new ArrayList<Mariage>();		//on donne � chaque citoyen une nouvelle liste de mariage 
    	mort = new Deces(this);
    }
    
    public Citoyen(int id, String nom, String p, Naissance n, String sexe, Mairie m){
    	identifiant = id;
    	this.nom = nom;
    	nomUsage = nom;
    	prenom = p;
    	naissance = n;
    	this.sexe = sexe;
    	mairieDeResidence = m;
    	enfant = new ArrayList<Naissance>();	//on donne � chaque citoyen une nouvelle liste d'enfants
    	mariage = new ArrayList<Mariage>();		//on donne � chaque citoyen une nouvelle liste de mariage 
    	mort = new Deces(this);
    }
    
    //M�thodes de la classe
    public void setNaissance(Naissance n) {		//permet de rentrer la date de naissance
    	naissance = n;
    }
    
    public Naissance getNaissance() {			//recherche la date de naissance
    	return naissance;
    }
    
    public void setMort(Deces m) {				//permet d'appliquer l'attribut mort � une personne qui vient de d�c�der
    	mort = m;
    } 
    
    public Deces getMort() {					//recherche les informations du d�c�s
    	return mort;
    }
    
    public void setMairie(Mairie m) {			//place la ville dans laquelle habite la citoyen
    	mairieDeResidence = m;
    }
    
    public Mairie getMairie() {					//recherche la ville dans laquelle habite la citoyen
    	return mairieDeResidence;
    }
    
    public void ajoutMariage(Mariage m) {		//ajout d'un mariage dans la liste des mariages du citoyen
    	mariage.add(m);
    }
    
    public void ajoutNaissance(Naissance n) {	//ajout d'un enfant dans la liste des enfants d'un citoyen
    	enfant.add(n);
    }
    
    public boolean estMarie() {					//v�rification si un citoyen est en ce moment m�me mari�
    	if (mariage.isEmpty()) { 				//si sa liste de mariages est vide
    		return false;						//la m�thode retourne faux
    		}
    	int i = mariage.size()-1;				//si l'emplacement est vide dans la liste des divorces � l'indice correspondant celui du dernier mariage dans la liste des marriages 
    	if ((mariage.get(i).divorce==null)&&(estVeuf()==false)) { //et que la personne n'est pas veuve
    		return true;						//alors le m�thode retourne vrai
    	} 
    	else {return false;}					//en dehors de ces conditions, la m�thode retourne faux
    }
    
    public boolean ageLegal(int age) {
    	if(calculAge()>=age) {
    		return true;
    	}
    	return false;
    }
    
    public boolean estMort() {					//on v�rifie si un citoyen est d�c�d�
    	if(mort.dateDeces==null) {				//si il n'existe pas de date de d�c�s, le citoyen est en vie
    		return false;						//la m�thode retourne faux
    	}
    	else {
    		return true;						//sinon, le citoyen est bien mort, la m�thode retourne vrai
    	}
    }

    public boolean estVeuf() { 					//on v�rifie si une personne est veuve
    	if (mariage.isEmpty()) { 				//c'est automatiquement faux si le citoyen n'a jamais �t� mari�
    		return false;
    		}
    	int i = mariage.size()-1;				//si le conjoint du dernier mariage n'a pas de date de d�c�s
    		if (mariage.get(i).conjoint2.mort.dateDeces == null) {
    			return false;					//alors le citoyen n'est pas veuf
    		}     	else {
        		return true;					//si il y a une date de d�c�s, alors la m�thode retourne vrai
        	}
    }
    
    
    public boolean estDivorce() {				//on v�rifie si une personne est divorc�e
    	if (mariage.isEmpty()) { 				//si sa liste de mariage est vide, il n'y a eu automatiquement aucun divorce
    		return false;						//la m�thode retourne faux
    		}
    	int i = mariage.size()-1;
    	if (mariage.get(i).divorce==null) {		//si l'emplacement est vide dans la liste des divorces � l'indice correspondant celui du dernier mariage dans la liste des marriages
    		return false;						//alors aucun divorce n'est associ� au dernier mariage, retourne faux
    	} else {return true;}					//sinon, retourne vrai
    }
    
    
    public boolean estParentDe(Citoyen x) {		
    	for (int i=0; i<enfant.size(); i++) {
    		if((identifiant == x.enfant.get(i).parent1.identifiant)|| (x.identifiant == x.enfant.get(i).parent2.identifiant) ) {
    			return true;
    		}
    	}
    	return false;
    }

    public boolean estEnfantDe(Citoyen x) {		//on v�rifie si l'identifiant de l'enfant est le m�me que ???
    	if((identifiant == x.naissance.parent1.identifiant)|| (identifiant == x.naissance.parent2.identifiant) ) {
    			return true;
    	} 
    	return false;
    	
    }

    
    public String getSex() {					//r�cup�re le sexe du citoyen
    	return sexe;
    }

    public void setSex(String ns) {				//pemet d'entrer le sexe de la personne
    	if(ns.equals("m")) {					//ne peut entrer que "m"
    		sexe = ns;	
    	}
    	if(ns.equals("f")) {					//ou "f", les deux seuls sexes accept�s par le programme
    		sexe = ns;	
    	}
    }

    public int getIdentifiant() {				//r�cup�re l'identifiant national du citoyen
    	return identifiant;
    }

    public void setIdentifiant(int id) {		//permet d'entrer l'identifiant national du citoyen
    	identifiant=id;
    
    }
    
    public void changeName(Citoyen x, String nouveauNom) {
    	x.nomUsage = nouveauNom;				//permet � un citoyen de changer son nom d'usage, apr�s un mariage par exemple
    }
     
    public void affichage() {					//affichage des donn�es et informations d'un citoyen
    	SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy HH:mm");
    	if(nomUsage!=nom) {						//dans le cas d'un nom d'usage diff�rent du nom initial,
    		System.out.print("Nom : " +nom);
    		if(sexe=="f") {						//on genre le terme "�poux" puis on indique le nom d'usage
    			System.out.println(", �pouse : " +nomUsage);
    		} else {
    			System.out.println(", �poux : " +nomUsage);
    		}
    	} else { 
    		System.out.println("Nom : " +nom);	//si les deux noms sont identiques, on indique seulement le nom
    	}
    	System.out.println("Prenom : " +prenom);
    	System.out.println("Date de naissance : " +sdf.format(naissance.dateNaissance));
    	System.out.println("sexe : " +sexe);
    	
    	
    	//permet d'indiquer l'�tat civil actuel de la personne, il sera genr� syst�matiquement
    	if (estMarie()==true) {					//si la personne est mari�e, on indique les informations qui suivent
    		int i = mariage.size()-1;
    		if(sexe=="f") {						
    			System.out.println("Etat civil : mari�e" );
    		} else {
    			System.out.println("Etat civil : mari�" );
    		}
    		System.out.println("Date du marriage : "+mariage.get(i).dateMariage);
    		System.out.println("Nom du conjoint : " +mariage.get(i).conjoint2.nom);
    	    System.out.println("Pr�nom du conjoint : " +mariage.get(i).conjoint2.prenom);
    	} else if (estDivorce()==true) {		//si la personne est divorc�e
    		if(sexe=="f") {						
    			System.out.println("Etat civil : c�libataire (divorc�e)" );
    		} else {
    			System.out.println("Etat civil : c�libataire (divorc�)" );
    		}	
    	}else if (estVeuf()==true) {			//si la personne est veuve
    		if(sexe=="f") {						
    			System.out.println("Etat civil : c�libataire (veuve)" );
    		} else {
    			System.out.println("Etat civil : c�libataire (veuf)" );
    		}
    	} else if(estMort()==true) {			//si la personne est d�c�d�e
    		System.out.println("Citoyen d�c�d�");
    	}else {									//si la personne n'est rien de tout cela, elle est donc c�libataire
    		System.out.println("Etat civil : c�libataire" );
    	}
    }
    
    public int calculAge(){				//on calcule la diff�rence entre la date du jour et la date de naissance du citoyen
    	Date dateDuJour = new Date();
	    
    	//on r�cup�re les variables qui vont permettre de d�duire l'�ge r�el de la personne
		int annee = (dateDuJour.getYear()+1916) - (naissance.dateNaissance.getYear()+1916);
		int mois = (dateDuJour.getMonth()+1) - (naissance.dateNaissance.getMonth()+1);
	    int jour = (dateDuJour.getDay()) - (naissance.dateNaissance.getDay());
	    int heures = (dateDuJour.getHours()) - (naissance.dateNaissance.getHours());
	    int min = (dateDuJour.getMinutes()) - (naissance.dateNaissance.getMinutes());
	    
	    //en fonction de la date de naissance, si elle a eu lieu avant le jour actuel dans l'annee, une simple soustraction ne suffit pas
	    if (min<0) {
	    	heures = heures - 1;
	    	min = 60 + min;
	    }
	    if(heures<0) {
	    	jour = jour-1;
	    	heures = 24 + heures;
	    }
	    if(jour<0) {
	    	if((mois==1)||(mois==3)||(mois==5)||(mois==7)||(mois==8)||(mois==10)||(mois==12)) {
	    	 	jour = 31 + jour;
	    	}else if((mois==4)||(mois==6)||(mois==9)||(mois==11)){
	    		jour = 30 + jour;
	    	}else {
	    		jour = 28 + jour;
	    	}
	    mois = mois - 1;
	    }
	    if(mois<0) {
	    	annee = annee - 1;
	    	mois = 12 - mois;
	    }
	    if(annee<0) {				//si annee est <0, cela veut dire que l'ann�e de la date de naissance �tait sup�rieure
	    							//� l'ann�e de la date actuelle, et c'est impossible
	    	System.out.println("L'ann�e de naissance est mauvaise");
	    }
	    return annee;				//on retourne l'ann�e de l'�ge car seule cette valeur nous int�resse
		}

    	
}
    
