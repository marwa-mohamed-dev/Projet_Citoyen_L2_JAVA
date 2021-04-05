package projetCitoyen;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class main {
	
	static Scanner scan = new Scanner(System.in);		//initialisation de scanner qu'on utilissera tout au long du programme
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		//date sous la forme SimpleDateFormat
	
	public static boolean isDateValide(String strDate) {		//test de validit� du format de la date entr�e
		try {
			sdf.setLenient(false);
			sdf.parse(strDate);
			return true;										//on retourne vrai si le format est bon
		} catch (ParseException e) {
			return false;										//faux si le format n'est pas le bon
		}
	}
	public static Date entrerDate(String strDate) {				//permet de convertir une date de type String en type Date
		try {
			Date d = sdf.parse(strDate);
			return d;											//date retourn�e de type Date
		} catch (ParseException e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}
	public static int newIdentifiant(ArrayList<Mairie> listMairies) {		//cr�er l'identifiant national
		int id=0;
		for (int i = 0; i<listMairies.size(); i++) {
			id = id +listMairies.get(i).listCitoyens.size()+listMairies.get(i).listDeces.size()+1;
		}
			return id;		//identifiant �gal � la somme du nombre de citoyens et du nombre de d�c�s par mairie
	}
	
	public static void affichageMenu() {		//affichage du menu, des diff�rentes options du programme � choisir
		System.out.println("\n Veuillez choisir une option du menu :");
		System.out.println("1 : Mariage");
		System.out.println("2 : Divorce");
		System.out.println("3 : Naissance");
		System.out.println("4 : Adoption");
		System.out.println("5 : D�c�s");
		System.out.println("6 : Affichage de l'�tat civil d'une personne");
		System.out.println("7 : Affichage de la liste des citoyens d'une mairie");
		System.out.println("8 : Saisie d'une nouveau citoyen");
		System.out.println("9 : Quitter le programme");
	}
	
	public static Mairie choixMairie(ArrayList<Mairie> listMairies) {	//permet d'indiquer dans quelle mairie on se place
		int numMairie;
		System.out.print("Entrez le num�ro de la mairie concern�e : ");
		numMairie = scan.nextInt();
		for (int i = 0; i<listMairies.size(); i++) {				//on cherche dans la liste des mairies
			if (numMairie == listMairies.get(i).numVille) {			//si les num�ros de ville coincident,
				Mairie m= listMairies.get(i);						//on r�cup�re les informations de la mairie d'int�r�t
				System.out.println("Le nom de la ville est "+listMairies.get(i).nomVille+".");
				return m;											//la m�thode retourne ces informations
			} 
		}
		return null;												//si la mairie vis�e n'existe pas, on ne renvoie rien
	}	
		
	public static Citoyen trouverCitoyen(ArrayList<Mairie> listMairies) {	 //permet de trouver le citoyen d'int�r�t
		System.out.println("Entrez l'identifiant du citoyen : ");
		int id1 = scan.nextInt();
		for (int i = 0; i<listMairies.size(); i++) {						 //on le cherche dans toutes les mairies
			for (int j = 0; j<listMairies.get(i).listCitoyens.size(); j++) { //et donc toutes les listes de citoyens
				if (id1 == listMairies.get(i).listCitoyens.get(j).identifiant) {
					Citoyen x = listMairies.get(i).listCitoyens.get(j);		//si on trouve l'identifiant recherch�
					return x;	//on renvoie les informations du citoyen dont l'identifiant est �gal � celui demand�
				} 
			}
		}
		return null;		//si la recherche n'aboutie pas, on renvoie null
	}
		
	public static Citoyen choixNom(Citoyen x, Citoyen y) {	//choix d'un nom commun (mariage ou enfant)
		System.out.println("1 : "+x.nom);
		System.out.println("2 : "+y.nom);
		int choix = scan.nextInt();							//l'utilisateur fait le choix
		if(choix == 1) {
			return x;										//on retourne le citoyen dont il faut extraire le nom
		} 
		return y;
	}
	
	//m�thode qui permet d'int�grer un nouveau citoyen � la base de donn�e
	public static Citoyen CreerCitoyen(Mairie m, int identifiant,String nom) {
		int id = identifiant;
		//String Id = String.valueOf(id);							//essai d'undividualisation des citoyens (pas que x, y, z)
		//String StringId = Integer.toString(id);
		System.out.println("Date de format jj/MM/aaaa hh:mm : ");	//on enregistre la date de naissance
		System.out.print("(Ex : 17/01/2000 07:08)");
		String strDate = scan.nextLine();
		System.out.print(" ");
		strDate = scan.nextLine();
		while (isDateValide(strDate)==false) {		//v�rification du format de la date demand�e
			System.out.println("La date n'est pas dans le bon format.");
			System.out.println("Veuillez entrer la date de naissance  de format jj/MM/aaaa hh:mm :");
			strDate = scan.nextLine();				//on la redemande jusqu'� ce qu'elle soit dans le bon format
		} 
		
		Date dateNaissance = entrerDate(strDate);	//on associe la date rentr�e � la date de naissance
		Naissance n = new Naissance(dateNaissance);	//cr�ation d'une naissance
		System.out.print("Prenom : ");				//on enregistre le pr�nom
		String prenom = scan.nextLine();
		prenom = scan.nextLine();
		Citoyen z = new Citoyen(id,nom,prenom);		//on construit le citoyen avec les informations remplies
		z.setNaissance(n);							//on lui applique la naissance cr��e
		System.out.print("Sexe (f ou m) : ");
		String sexe = scan.nextLine();				//on demande et r�cup�re le sexe du nouveau citoyen
		sexe = scan.nextLine();	
		z.setSex(sexe);								//on donne le sexe choisi au citoyen
		z.setMairie(m);								//on lui attribue la mairie qui �tait en attribut
		return z;
	}
	
	///////////M�thode des actions lanc�es par le choix de l'utilisateur en partant du menu propos�
	public static ArrayList<Mairie> action(int choix, ArrayList<Mairie> listMairies) {
		Date dateDuJour = new Date();					//on d�clare la variable de la date du jour actuel
		//essai//
		Mairie m = listMairies.get(0);					
		Citoyen x = new Citoyen(1,"n1","p1");
		Citoyen y = new Citoyen(2,"n2","p2");
		//essai//	
			
		switch(choix) {			//liste des choix de l'utilisateur et des actions produites cons�quentes
		
		///choix 1 : Mariage///
		case 1 :				
			
			m = choixMairie(listMairies);		//on se place dans une mairie pour effectuer le mariage
			if(m==null) {						//si elle n'existe pas, l'utilisateur doit recommencer le choix 1
				System.out.println("La mairie n'existe pas, veuillez r�essayer.");
				break;
				}
			x = trouverCitoyen(listMairies);	//on cherche le citoyen et on r�cup�re ses informations
			if(x==null) {						
				System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
				break;
			}
			y = trouverCitoyen(listMairies);	//de m�me pour le deuxi�me citoyen du mariage
			if(y==null) {
				System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
				break;
			}if(x.estParentDe(y)==true) { //on v�rifie que l'enfant ne soit pas d�j� enregistrer
				System.out.println(x.nom+" "+x.prenom+" est enregistr� en tant que parent de "+y.nom+" "+y.prenom);
				break;
			}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la m�me personne
				System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
				break;
			}
			if(x.estMarie() == true) {			//v�rification pour les deux futurs mari�s si ils ne le sont pas d�j�
				System.out.println(x.nom+" "+x.prenom+" est d�j� mari�.");
				break;
			}
			else if(y.estMarie() == true) {
				System.out.println(y.nom+" "+y.prenom+" est d�j� mari�.");
				break;
			} else 	if(x.ageLegal(18) == false) {	//v�rification pour les deux futurs mari�s si ils sont majeurs
				System.out.println(x.nom+" "+x.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
				break;
			}
			else if(y.ageLegal(18) == false) {
				System.out.println(y.nom+" "+y.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
				break;
				
			/////////Fin des v�rifications pr�alables, on peut proc�der au mariage
			}else{							
				m.listMariages.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste de la mairie
				
				/////////proc�dure pour le premier conjoint
				x.mariage.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste personnelle du citoyen
				int i = x.mariage.size()-1;						//on se place dans le dernier mariage du citoyen
				x.mariage.get(i).setMairie(m);					//association de la mairie au mariage tout juste cr��
				
				////////m�me proc�dure pour le deuxi�me conjoint
				y.mariage.add((new Mariage(y,x,dateDuJour)));	//le citoyen concern� est toujours le conjoint1
				i = y.mariage.size()-1;
				y.mariage.get(i).setMairie(m);
				
				System.out.println("Saisissez le choix du nom de famille commun du couple"); //choix du nom de famille
				if(choixNom(x,y)==x) {							
					y.changeName(y, x.nom);
				} else {
					x.changeName(x, y.nom);
				}
				System.out.println("Le nom de famille choisi est " +x.nomUsage);
				System.out.println(x.nomUsage +" "+ x.prenom+" et " +y.nomUsage+" "+y.prenom+" sont maintenant mari�s dans la mairie de "+m.nomVille+".");
				break;
			}
				
		///choix 2 : Divorce///
		case 2 :			
			
			x = trouverCitoyen(listMairies);		//on cherche chacun des deux citoyens dans les listes
			if(x==null) {							//v�rification de leur existence
				System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
				break;
			}
			y = trouverCitoyen(listMairies);
			if(y==null) {
				System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
				break;
			}
			if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la m�me personne
				System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
				break;
			}
			if ((x.mariage.isEmpty())||(y.mariage.isEmpty())) {	//si l'un des deux n'a jamais �t� mari�, pas de divorce possible
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" ne sont pas mari�s entre eux.");
				break;
			}
			
			//////////apr�s les v�rifications de base, on effectue le divorce
		    int i = x.mariage.size()-1;
		   	if (x.mariage.get(i).conjoint2 == y) {			//on v�rifie qu'ils sont bien mari�s l'un � l'autre
	    		x.mariage.get(i).setDivorce(dateDuJour);	//on applique la date du jour en tant que date du divorce
				i = y.mariage.size()-1;
				y.mariage.get(i).setDivorce(dateDuJour);	//on fait de m�me pour le deuxi�me citoyen
				Mariage.repriseNomInitial(x,y);				//le citoyen ayant chang� de nom d'usage reprend son nom d'origine
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" sont maintenant divorc�s.");
				break;
		   	} else {										//dans le cas o� ils sont mari�s mais pas l'un � l'autre
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" ne sont pas mari�s entre eux.");
				break;
		   	}
			   
		///choix 3 : Naissance///
		case 3 :
			
			m = choixMairie(listMairies);		//on se place dans une mairie pour effectuer la naissance		
			if(m==null) {
				System.out.println("la mairie n'existe pas, veuillez r�essayer.");
				break;
			}
			System.out.println("Situation monoparentale ? (1 : Oui/ 2 : Non) ");
			int reponse = scan.nextInt();
			
			if(reponse==1) {
				x = trouverCitoyen(listMairies);			//on cherche si le parent existe
				if(x==null) {
					System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
					break;
				}
				int id = newIdentifiant(listMairies);		//on r�cup�re un nouvel identifiant national
				String nomEnfant = x.nomUsage;				//le nom de l'enfant est le nom d'usage du parent unique
				System.out.println("Veuillez entrer la date de naissance du nouveau-n�.");
				Citoyen z = CreerCitoyen(m,id,nomEnfant);	//le nouveau-n� est cr��
				z.setNaissance(new Naissance(x,z.naissance.dateNaissance));		  //on applique les donn�es de la naissance au nouveau citoyen
				
				m.listNaissances.add(new Naissance(x,z.naissance.dateNaissance)); //on ajoute cette naissance � la liste des naissances de la mairie
				z.naissance.setMairie(m);					//on donne une mairie � la naissance
				m.listCitoyens.add(z);						//on ajoute le nouveau-n� � la liste des citoyens de la mairie de naissance
					
				System.out.println("Le nouveau-n� a bien �t� enregistr� aupr�s de la mairie.");
				x.ajoutNaissance(z.naissance);				//ajout du nouveau-n� dans la liste des enfants du parent
				Naissance.enregistrementN(z,x);						//on v�rifie que le lien enfant-parent a bien �t� fait
			}else {
				/////////m�mes �tapes pour une naissance avec les deux parents pr�sents
				x = trouverCitoyen(listMairies);			
				y = trouverCitoyen(listMairies);
				if(x==null) {
					System.out.println("L'identifiant du parent n'est pas enregistr�, r�essaiyez.");
					break;
				}
				if(y==null) {
					System.out.println("L'identifiant du parent n'est pas enregistr�, r�essaiyez.");
					break;
				}
				if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la m�me personne
					System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
					break;
				}
				if ((x.sexe=="f"&&y.sexe=="m")||(x.sexe=="m"&&y.sexe=="f")) { //on v�rifie que les sexes soient biologiquement compatibles (homme/femme)
					int id = newIdentifiant(listMairies);
					String nomEnfant = Mariage.nomEnfant(x,y);		//
					System.out.println("Veuillez entrer la date de naissance du nouveau-n�.");
					Citoyen z = CreerCitoyen(m,id,nomEnfant);
					z.setNaissance(new Naissance(x,y,z.naissance.dateNaissance));
					m.listNaissances.add(new Naissance(x,y,z.naissance.dateNaissance));
					m.listCitoyens.add(z);
					System.out.println("Le nouveau-n� a bien �t� enregistr� aupr�s de la mairie.");
					
					/////////actions doubl�es par rapport � situation monoparentale, ici � faire pour les deux parents
					x.ajoutNaissance(z.naissance);			
					y.ajoutNaissance(z.naissance);
					z.naissance.setMairie(m);
					Naissance.enregistrementN(z,y);
					Naissance.enregistrementN(z,x);
				} else {
					System.out.println("La naissance ne peut �tre enregistr�e, les g�niteurs sont de m�me sexe.");
				}
			}
			break;
				
		///choix 4 : Adoption///
		case 4 :				
			
			System.out.println("Situation monoparentale ? (1 : Oui/ 2 : Non) ");
			reponse = scan.nextInt();
			
			if(reponse==1) {
				x = trouverCitoyen(listMairies);		//on cherche le parent adoptif
				if(x==null) {
					System.out.println("L'identifiant du parent adoptif n'est pas enregistr�, r�essaiyez.");
					break;
				}
				if(x.estParentDe(y)==true) { //on v�rifie que l'enfant ne soit pas d�j� enregistrer
					System.out.println(y.nom+" "+y.prenom+" est d�j� enregistr� en tant que parent de "+x.nom+" "+x.prenom);
					break;
				}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la m�me personne
					System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
					break;
				}
				if(x.ageLegal(28)==false) {
					System.out.println("Le parent souhaitant adopter n'a pas l'�ge recquis (>28 ans).");
					break;
				}
				System.out.println("Pour la personne adopt�e,");
				m = x.getMairie();						//on r�cup�re la mairie du parent
				y = trouverCitoyen(listMairies);		//on cherche la personne qui va �tre adopt�e
				if(y==null) {							//on v�rifie si elle existe bien
					System.out.println("L'identifiant de l'enfant � adopter n'est pas enregistr�, r�essaiyez.");
					break;
				}
				y.mairieDeResidence.listCitoyens.remove(y);	//on retire le citoyen qui va �tre adopt� de sa mairie actuelle
				m.listCitoyens.add(y);					//on l'ajoute ensuite � la liste de citoyens de la mairie du parent adoptif
				y.setMairie(m);							//on attribue la mairie du parent au citoyen adopt�
				y.nom = x.nomUsage;						//le citoyen adopt� prend le nom de son parent adoptif
				y.nomUsage = x.nomUsage;				//son nom et son nom d'usage sont modifi�s
				y.affichage();
				x.ajoutNaissance(y.naissance);			//on ajoute le citoyen adopt� dans la liste des enfants du parent
				System.out.println(y.nom+" "+y.prenom+" est enregistr� en tant qu'enfant de "+x.nom+" "+x.prenom);
			}else {
				/////////m�mes �tapes pour une naissance avec les deux parents pr�sents
				x = trouverCitoyen(listMairies);
				y = trouverCitoyen(listMairies);
				if(x==null) {
					System.out.println("L'identifiant du parent n'est pas enregistr�, r�essaiyez.");
					break;
				}
				if(y==null) {
					System.out.println("L'identifiant du parent n'est pas enregistr�, r�essaiyez.");
					break;
				}
				if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la m�me personne
					System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
					break;
				}
				i = x.mariage.size()-1;
				if((x.ageLegal(28)==false)||(y.ageLegal(28)==false)) {	//v�rification de l'�ge des futurs parents adoptifs
					System.out.println("Un des parents au moins souhaitant adopter n'a pas l'�ge recquis (>28 ans).");
					break;
				}
				if (x.mariage.get(i).conjoint2 == y) {		//on v�rifie que les parents adoptifs sont mari�s entre eux
					System.out.println("Pour la personne adopt�e : ");
					Citoyen z = trouverCitoyen(listMairies);
					if(z==null) {
						System.out.println("L'un des identifiants des parents n'est pas enregistr�, r�essaiyez.");
						break;
					}
					if(x.estParentDe(z)==true) { //on v�rifie que l'enfant ne soit pas d�j� enregistrer
						System.out.println(x.nom+" "+x.prenom+" est d�j� enregistr� en tant que parent de "+z.nom+" "+z.prenom);
						break;
					} else if(y.estParentDe(z)==true) {
						System.out.println(y.nom+" "+y.prenom+" est d�j� enregistr� en tant que parent de "+z.nom+" "+z.prenom);
						break;
					}else if((x.identifiant==z.identifiant)||(y.identifiant==z.identifiant)) { // on verifie qu'il ne s'agit pas de la m�me personne
						System.out.println("il s'agit de la m�me personne, v�rifiez les identifiants");
						break;
					}
					m = x.getMairie();
					z.mairieDeResidence.listCitoyens.remove(z);
					m.listCitoyens.add(z);
					z.setMairie(m);
					z.nom = x.nomUsage;						//transmission du nom commun des parents � l'adopt�
					z.nomUsage = x.nomUsage;
					x.ajoutNaissance(z.naissance);			
					y.ajoutNaissance(z.naissance);
					System.out.println(z.nom+" "+z.prenom+" est enregistr� en tant qu'enfant de "+y.nom+" "+y.prenom+" et "+x.nom+" "+x.prenom+".");
			   	} else {									//les parents adoptifs ne sont pas mari�s entre eux
			   		System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+"ne sont pas mari�s.");
					System.out.println("L'adoption ne peut se faire si les deux parents ne sont pas mari�s.");
			   	}
			}
			break;
				
		///choix 5 : D�c�s///
		case 5 :				
			
			x = trouverCitoyen(listMairies);				//on cherche le citoyen d�c�d� dans les listes de citoyen
			if(x==null) {
				System.out.println("L'identifiant du suppos� d�funt n'est pas enregistr�, r�essaiyez.");
				break;
			}
			m = x.getMairie();								//on r�cup�re sa mairie
			m.listDeces.add((new Deces(x,dateDuJour)));		//on ajoute le d�c�s du citoyen � la liste des d�c�s de la mairie
			m.listCitoyens.remove(x);						//on enl�ve le citoyen de la liste des citoyens vivants
			x.setMort(new Deces(x,dateDuJour));				//on applique le d�c�s � la personne d�c�d�e
			x.mort.setMairie(x.getMairie());				//on lui applique une mairie de d�c�s qui est la m�me que celle de r�sidence
			System.out.println("Le citoyen "+x.nom+" "+x.prenom+" a �t� enregistr� d�c�d� le " +sdf.format(x.mort.dateDeces)+".");
			
			if (x.estMarie()==true){		//on v�rifie si le d�funt �tait mari�				
				i = x.mariage.size()-1;
				Citoyen veuf = x.mariage.get(i).conjoint2;	//on r�cup�re le conjoint du d�funt
				if(veuf.nomUsage == x.nom) {				//test si c'�tait le conjoint qui avait pris le nom du d�funt lors du mariage
					veuf.changeName(veuf,veuf.nom);			//changement du nom au nom originel pour la personne veuve
					System.out.println("Le conjoint "+x.nom+" "+x.prenom+" a repris son nom de naissance.");
					if(veuf.estVeuf()==true) {				//v�rification du changement de statut
						System.out.println("Le statut du conjoint "+veuf.nom+" "+veuf.prenom+" a bien �t� modifi�.");
					}
				}
			}
			break;
				
		///choix 6 : Affichage de l'�tat civil///
		case 6 :
			
			x = trouverCitoyen(listMairies);	//on cherche le citoyen � afficher
			if(x==null) {						//v�rification de l'existence du citoyen
				System.out.println("L'identifiant n'est pas enregistr�, r�essaiyez.");
				break;
			}
			x.affichage();						//on affiche les informations du citoyen trouv�
			break;
				
		///choix 7 : Affichage de la liste des citoyens d'une mairie choisie///
		case 7 : 
			
			m = choixMairie(listMairies);
			if(m==null) {						//v�rification de l'existence de la mairie recherch�e		
				System.out.println("La mairie n'existe pas, veuillez r�essayer.");
				break;
			}
			for (int j = 0; j<m.listCitoyens.size(); j++) {		//on parcours la liste des citoyens de la mairie d'int�r�t
				m.listCitoyens.get(j).affichage();				//on affiche les citoyens un par un 
				System.out.println("");
			}
			int nbhabitant = m.listCitoyens.size();				//on affiche le nombre total d'habitants de la ville
			System.out.println("Le nombre d'habitants de la ville "+m.nomVille+" est "+nbhabitant);
			break;
				
		///choix 8 : Saisie d'un nouveau citoyen///
		case 8 :
			
			m = choixMairie(listMairies);
			if(m==null) {						//v�rification de l'existence de la mairie recherch�e		
				System.out.println("La mairie n'existe pas, veuillez r�essayer.");
				break;
			}
			
			System.out.println("Est-ce un nouvel arrivant ? (1: oui /2: non)");
			reponse = scan.nextInt();
			if(reponse==1) {
				int id = newIdentifiant(listMairies);
				System.out.print("Nom : ");
				String nom = scan.nextLine();
				nom = scan.nextLine();
				Citoyen z = CreerCitoyen(m, id, nom); 	//on cr�er le citoyen � l'aide d'un constructeur dans la m�thode
				z.setMairie(m);							//on lui attribue la mairie dont on a d�cid� au pr�alable
				m.listCitoyens.add(z);					//on ajoute le nouveau citoyen � la liste de la mairie choisie
			}else {
				x = trouverCitoyen(listMairies);
				if(x==null) {							//v�rification de l'existence du citoyen
					System.out.println("L'identifiant du citoyen � d�placer n'est pas enregistr�, r�essaiyez.");
					break;
				}
				x.getMairie().listCitoyens.remove(x);
				x.setMairie(m);
				m.listCitoyens.add(x);
			}
			break;
		}		
		return listMairies;
	}
	
	public static ArrayList<Mairie> creerFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File ("fichierListMairies.txt");
		ArrayList<Mairie> listMairies = new ArrayList<Mairie>();		//cr�ation de la liste des mairies
		listMairies.add(new Mairie(1,"essai"));
		listMairies.add(new Mairie(2,"essai2"));
		listMairies.add(new Mairie(3,"essai3"));
		
		//on serialize la collections de mairies
		FileOutputStream fo = new FileOutputStream(file);
		ObjectOutputStream output = new ObjectOutputStream(fo);
		for(Mairie m : listMairies) {
			output.writeObject(m);
		}
		output.close();
		fo.close();
		
		//on deserialize la collection de mairies
		FileInputStream fi = new FileInputStream(file);
		ObjectInputStream input = new ObjectInputStream(fi);
		ArrayList<Mairie> listMairies2 = new ArrayList<Mairie>();
		try {
			while(true) {
				Mairie m = (Mairie)input.readObject();
				listMairies2.add(m);
			}
		} catch (EOFException ex) {
		}
		return listMairies2;
	}

	/////////////////////////////////////Main du programme
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ArrayList<Mairie> listMairies2 = creerFile();
		
		/////////////////////////////////////////////////////////////
		ArrayList<Citoyen> listCitoyens = new ArrayList<Citoyen>();		//cr�ation de la liste des mairies
		
		Naissance n = new Naissance(entrerDate("20/10/2000 12:55"));
		Naissance n2 = new Naissance(entrerDate("05/08/2000 13:11"));
		Naissance n3 = new Naissance(entrerDate("01/03/2000 14:10"));
		Naissance n4 = new Naissance(entrerDate("01/03/1968 14:10"));
		Naissance n5 = new Naissance(entrerDate("01/03/1974 14:10"));
		Naissance n6 = new Naissance(entrerDate("01/03/1999 14:10"));
		
		int a = 0;
		int b = 1;
		int c = 2;
		
		listCitoyens.add(new Citoyen(1,"n1","p1", n, "f", listMairies2.get(a)));
		listCitoyens.add(new Citoyen(2,"n2","p2", n2, "m", listMairies2.get(b)));
		listCitoyens.add(new Citoyen(3,"n3","p3", n3, "f", listMairies2.get(c)));
		listCitoyens.add(new Citoyen(4,"n4","p4", n4, "m", listMairies2.get(a)));
		listCitoyens.add(new Citoyen(5,"n5","p5", n5, "f", listMairies2.get(b)));
		listCitoyens.add(new Citoyen(6,"n6","p6", n6, "m", listMairies2.get(c)));
		
		for(int j = 0; j<listCitoyens.size(); j++) {
			if(listCitoyens.get(j).mairieDeResidence == listMairies2.get(a)) {
				listMairies2.get(a).listCitoyens.add(listCitoyens.get(j));
			} else if (listCitoyens.get(j).mairieDeResidence == listMairies2.get(b)) {
				listMairies2.get(b).listCitoyens.add(listCitoyens.get(j));
			} else {
				listMairies2.get(c).listCitoyens.add(listCitoyens.get(j));
			}
		}
		
		///////////////////////////////////
		boolean redo = true;					//bool�enne initialis�e � vrai
		while (redo == true){					//tant qu'elle est vraie, le menu est redemand� apr�s l'action
			affichageMenu();					//on affiche le menu
			System.out.print("Choix : ");		//l'utilisateur fait son choix d'action
			int choix = scan.nextInt();
			
			while ((choix<1) || (choix>9)) {	//dans le cas o� le choix est incorrect, l'utilisateur doit choisir � nouveau
				System.out.print("Choix incorrect. Veuillez r��crire : ");
				choix = scan.nextInt();
			}
			if (choix==9) {						
				System.out.println("Vous allez quitter le programme. \n Confirmer ? (1 : oui/2 : non)");
				int confirmation = scan.nextInt();		//on demande la confirmation de sortie du menu
				if(confirmation==1) {
					redo=false;							//bool�enne fausse donc on ne retournera pas dans la boucle
					System.out.println("Au revoir !");
				}else {
					System.out.println("Vous revenez au menu.");
				}
			}
			listMairies2 = action(choix,listMairies2);			//on lance l'action � effectuer en fonction du choix de l'utilisateur
		}
		
		File file2 = new File ("fichierListMairies.txt");		//sauvegarde dans fichier annexe
		FileOutputStream fo = new FileOutputStream(file2);
		ObjectOutputStream output = new ObjectOutputStream(fo);
		for(Mairie m : listMairies2) {
			output.writeObject(m);
			System.out.println("enregistr�");
		}
		output.close();
		fo.close();
	}		
}


