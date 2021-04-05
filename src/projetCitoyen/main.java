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
	
	public static boolean isDateValide(String strDate) {		//test de validité du format de la date entrée
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
			return d;											//date retournée de type Date
		} catch (ParseException e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}
	public static int newIdentifiant(ArrayList<Mairie> listMairies) {		//créer l'identifiant national
		int id=0;
		for (int i = 0; i<listMairies.size(); i++) {
			id = id +listMairies.get(i).listCitoyens.size()+listMairies.get(i).listDeces.size()+1;
		}
			return id;		//identifiant égal à la somme du nombre de citoyens et du nombre de décès par mairie
	}
	
	public static void affichageMenu() {		//affichage du menu, des différentes options du programme à choisir
		System.out.println("\n Veuillez choisir une option du menu :");
		System.out.println("1 : Mariage");
		System.out.println("2 : Divorce");
		System.out.println("3 : Naissance");
		System.out.println("4 : Adoption");
		System.out.println("5 : Décès");
		System.out.println("6 : Affichage de l'état civil d'une personne");
		System.out.println("7 : Affichage de la liste des citoyens d'une mairie");
		System.out.println("8 : Saisie d'une nouveau citoyen");
		System.out.println("9 : Quitter le programme");
	}
	
	public static Mairie choixMairie(ArrayList<Mairie> listMairies) {	//permet d'indiquer dans quelle mairie on se place
		int numMairie;
		System.out.print("Entrez le numéro de la mairie concernée : ");
		numMairie = scan.nextInt();
		for (int i = 0; i<listMairies.size(); i++) {				//on cherche dans la liste des mairies
			if (numMairie == listMairies.get(i).numVille) {			//si les numéros de ville coincident,
				Mairie m= listMairies.get(i);						//on récupère les informations de la mairie d'intérêt
				System.out.println("Le nom de la ville est "+listMairies.get(i).nomVille+".");
				return m;											//la méthode retourne ces informations
			} 
		}
		return null;												//si la mairie visée n'existe pas, on ne renvoie rien
	}	
		
	public static Citoyen trouverCitoyen(ArrayList<Mairie> listMairies) {	 //permet de trouver le citoyen d'intérêt
		System.out.println("Entrez l'identifiant du citoyen : ");
		int id1 = scan.nextInt();
		for (int i = 0; i<listMairies.size(); i++) {						 //on le cherche dans toutes les mairies
			for (int j = 0; j<listMairies.get(i).listCitoyens.size(); j++) { //et donc toutes les listes de citoyens
				if (id1 == listMairies.get(i).listCitoyens.get(j).identifiant) {
					Citoyen x = listMairies.get(i).listCitoyens.get(j);		//si on trouve l'identifiant recherché
					return x;	//on renvoie les informations du citoyen dont l'identifiant est égal à celui demandé
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
	
	//méthode qui permet d'intégrer un nouveau citoyen à la base de donnée
	public static Citoyen CreerCitoyen(Mairie m, int identifiant,String nom) {
		int id = identifiant;
		//String Id = String.valueOf(id);							//essai d'undividualisation des citoyens (pas que x, y, z)
		//String StringId = Integer.toString(id);
		System.out.println("Date de format jj/MM/aaaa hh:mm : ");	//on enregistre la date de naissance
		System.out.print("(Ex : 17/01/2000 07:08)");
		String strDate = scan.nextLine();
		System.out.print(" ");
		strDate = scan.nextLine();
		while (isDateValide(strDate)==false) {		//vérification du format de la date demandée
			System.out.println("La date n'est pas dans le bon format.");
			System.out.println("Veuillez entrer la date de naissance  de format jj/MM/aaaa hh:mm :");
			strDate = scan.nextLine();				//on la redemande jusqu'à ce qu'elle soit dans le bon format
		} 
		
		Date dateNaissance = entrerDate(strDate);	//on associe la date rentrée à la date de naissance
		Naissance n = new Naissance(dateNaissance);	//création d'une naissance
		System.out.print("Prenom : ");				//on enregistre le prénom
		String prenom = scan.nextLine();
		prenom = scan.nextLine();
		Citoyen z = new Citoyen(id,nom,prenom);		//on construit le citoyen avec les informations remplies
		z.setNaissance(n);							//on lui applique la naissance créée
		System.out.print("Sexe (f ou m) : ");
		String sexe = scan.nextLine();				//on demande et récupère le sexe du nouveau citoyen
		sexe = scan.nextLine();	
		z.setSex(sexe);								//on donne le sexe choisi au citoyen
		z.setMairie(m);								//on lui attribue la mairie qui était en attribut
		return z;
	}
	
	///////////Méthode des actions lancées par le choix de l'utilisateur en partant du menu proposé
	public static ArrayList<Mairie> action(int choix, ArrayList<Mairie> listMairies) {
		Date dateDuJour = new Date();					//on déclare la variable de la date du jour actuel
		//essai//
		Mairie m = listMairies.get(0);					
		Citoyen x = new Citoyen(1,"n1","p1");
		Citoyen y = new Citoyen(2,"n2","p2");
		//essai//	
			
		switch(choix) {			//liste des choix de l'utilisateur et des actions produites conséquentes
		
		///choix 1 : Mariage///
		case 1 :				
			
			m = choixMairie(listMairies);		//on se place dans une mairie pour effectuer le mariage
			if(m==null) {						//si elle n'existe pas, l'utilisateur doit recommencer le choix 1
				System.out.println("La mairie n'existe pas, veuillez réessayer.");
				break;
				}
			x = trouverCitoyen(listMairies);	//on cherche le citoyen et on récupère ses informations
			if(x==null) {						
				System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
				break;
			}
			y = trouverCitoyen(listMairies);	//de même pour le deuxième citoyen du mariage
			if(y==null) {
				System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
				break;
			}if(x.estParentDe(y)==true) { //on vérifie que l'enfant ne soit pas déjà enregistrer
				System.out.println(x.nom+" "+x.prenom+" est enregistré en tant que parent de "+y.nom+" "+y.prenom);
				break;
			}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
				System.out.println("il s'agit de la même personne, vérifiez les identifiants");
				break;
			}
			if(x.estMarie() == true) {			//vérification pour les deux futurs mariés si ils ne le sont pas déjà
				System.out.println(x.nom+" "+x.prenom+" est déjà marié.");
				break;
			}
			else if(y.estMarie() == true) {
				System.out.println(y.nom+" "+y.prenom+" est déjà marié.");
				break;
			} else 	if(x.ageLegal(18) == false) {	//vérification pour les deux futurs mariés si ils sont majeurs
				System.out.println(x.nom+" "+x.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
				break;
			}
			else if(y.ageLegal(18) == false) {
				System.out.println(y.nom+" "+y.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
				break;
				
			/////////Fin des vérifications préalables, on peut procéder au mariage
			}else{							
				m.listMariages.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste de la mairie
				
				/////////procédure pour le premier conjoint
				x.mariage.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste personnelle du citoyen
				int i = x.mariage.size()-1;						//on se place dans le dernier mariage du citoyen
				x.mariage.get(i).setMairie(m);					//association de la mairie au mariage tout juste créé
				
				////////même procédure pour le deuxième conjoint
				y.mariage.add((new Mariage(y,x,dateDuJour)));	//le citoyen concerné est toujours le conjoint1
				i = y.mariage.size()-1;
				y.mariage.get(i).setMairie(m);
				
				System.out.println("Saisissez le choix du nom de famille commun du couple"); //choix du nom de famille
				if(choixNom(x,y)==x) {							
					y.changeName(y, x.nom);
				} else {
					x.changeName(x, y.nom);
				}
				System.out.println("Le nom de famille choisi est " +x.nomUsage);
				System.out.println(x.nomUsage +" "+ x.prenom+" et " +y.nomUsage+" "+y.prenom+" sont maintenant mariés dans la mairie de "+m.nomVille+".");
				break;
			}
				
		///choix 2 : Divorce///
		case 2 :			
			
			x = trouverCitoyen(listMairies);		//on cherche chacun des deux citoyens dans les listes
			if(x==null) {							//vérification de leur existence
				System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
				break;
			}
			y = trouverCitoyen(listMairies);
			if(y==null) {
				System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
				break;
			}
			if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
				System.out.println("il s'agit de la même personne, vérifiez les identifiants");
				break;
			}
			if ((x.mariage.isEmpty())||(y.mariage.isEmpty())) {	//si l'un des deux n'a jamais été marié, pas de divorce possible
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" ne sont pas mariés entre eux.");
				break;
			}
			
			//////////après les vérifications de base, on effectue le divorce
		    int i = x.mariage.size()-1;
		   	if (x.mariage.get(i).conjoint2 == y) {			//on vérifie qu'ils sont bien mariés l'un à l'autre
	    		x.mariage.get(i).setDivorce(dateDuJour);	//on applique la date du jour en tant que date du divorce
				i = y.mariage.size()-1;
				y.mariage.get(i).setDivorce(dateDuJour);	//on fait de même pour le deuxième citoyen
				Mariage.repriseNomInitial(x,y);				//le citoyen ayant changé de nom d'usage reprend son nom d'origine
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" sont maintenant divorcés.");
				break;
		   	} else {										//dans le cas où ils sont mariés mais pas l'un à l'autre
				System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" ne sont pas mariés entre eux.");
				break;
		   	}
			   
		///choix 3 : Naissance///
		case 3 :
			
			m = choixMairie(listMairies);		//on se place dans une mairie pour effectuer la naissance		
			if(m==null) {
				System.out.println("la mairie n'existe pas, veuillez réessayer.");
				break;
			}
			System.out.println("Situation monoparentale ? (1 : Oui/ 2 : Non) ");
			int reponse = scan.nextInt();
			
			if(reponse==1) {
				x = trouverCitoyen(listMairies);			//on cherche si le parent existe
				if(x==null) {
					System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
					break;
				}
				int id = newIdentifiant(listMairies);		//on récupère un nouvel identifiant national
				String nomEnfant = x.nomUsage;				//le nom de l'enfant est le nom d'usage du parent unique
				System.out.println("Veuillez entrer la date de naissance du nouveau-né.");
				Citoyen z = CreerCitoyen(m,id,nomEnfant);	//le nouveau-né est créé
				z.setNaissance(new Naissance(x,z.naissance.dateNaissance));		  //on applique les données de la naissance au nouveau citoyen
				
				m.listNaissances.add(new Naissance(x,z.naissance.dateNaissance)); //on ajoute cette naissance à la liste des naissances de la mairie
				z.naissance.setMairie(m);					//on donne une mairie à la naissance
				m.listCitoyens.add(z);						//on ajoute le nouveau-né à la liste des citoyens de la mairie de naissance
					
				System.out.println("Le nouveau-né a bien été enregistré auprès de la mairie.");
				x.ajoutNaissance(z.naissance);				//ajout du nouveau-né dans la liste des enfants du parent
				Naissance.enregistrementN(z,x);						//on vérifie que le lien enfant-parent a bien été fait
			}else {
				/////////mêmes étapes pour une naissance avec les deux parents présents
				x = trouverCitoyen(listMairies);			
				y = trouverCitoyen(listMairies);
				if(x==null) {
					System.out.println("L'identifiant du parent n'est pas enregistré, réessaiyez.");
					break;
				}
				if(y==null) {
					System.out.println("L'identifiant du parent n'est pas enregistré, réessaiyez.");
					break;
				}
				if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
					System.out.println("il s'agit de la même personne, vérifiez les identifiants");
					break;
				}
				if ((x.sexe=="f"&&y.sexe=="m")||(x.sexe=="m"&&y.sexe=="f")) { //on vérifie que les sexes soient biologiquement compatibles (homme/femme)
					int id = newIdentifiant(listMairies);
					String nomEnfant = Mariage.nomEnfant(x,y);		//
					System.out.println("Veuillez entrer la date de naissance du nouveau-né.");
					Citoyen z = CreerCitoyen(m,id,nomEnfant);
					z.setNaissance(new Naissance(x,y,z.naissance.dateNaissance));
					m.listNaissances.add(new Naissance(x,y,z.naissance.dateNaissance));
					m.listCitoyens.add(z);
					System.out.println("Le nouveau-né a bien été enregistré auprès de la mairie.");
					
					/////////actions doublées par rapport à situation monoparentale, ici à faire pour les deux parents
					x.ajoutNaissance(z.naissance);			
					y.ajoutNaissance(z.naissance);
					z.naissance.setMairie(m);
					Naissance.enregistrementN(z,y);
					Naissance.enregistrementN(z,x);
				} else {
					System.out.println("La naissance ne peut être enregistrée, les géniteurs sont de même sexe.");
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
					System.out.println("L'identifiant du parent adoptif n'est pas enregistré, réessaiyez.");
					break;
				}
				if(x.estParentDe(y)==true) { //on vérifie que l'enfant ne soit pas déjà enregistrer
					System.out.println(y.nom+" "+y.prenom+" est déjà enregistré en tant que parent de "+x.nom+" "+x.prenom);
					break;
				}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
					System.out.println("il s'agit de la même personne, vérifiez les identifiants");
					break;
				}
				if(x.ageLegal(28)==false) {
					System.out.println("Le parent souhaitant adopter n'a pas l'âge recquis (>28 ans).");
					break;
				}
				System.out.println("Pour la personne adoptée,");
				m = x.getMairie();						//on récupère la mairie du parent
				y = trouverCitoyen(listMairies);		//on cherche la personne qui va être adoptée
				if(y==null) {							//on vérifie si elle existe bien
					System.out.println("L'identifiant de l'enfant à adopter n'est pas enregistré, réessaiyez.");
					break;
				}
				y.mairieDeResidence.listCitoyens.remove(y);	//on retire le citoyen qui va être adopté de sa mairie actuelle
				m.listCitoyens.add(y);					//on l'ajoute ensuite à la liste de citoyens de la mairie du parent adoptif
				y.setMairie(m);							//on attribue la mairie du parent au citoyen adopté
				y.nom = x.nomUsage;						//le citoyen adopté prend le nom de son parent adoptif
				y.nomUsage = x.nomUsage;				//son nom et son nom d'usage sont modifiés
				y.affichage();
				x.ajoutNaissance(y.naissance);			//on ajoute le citoyen adopté dans la liste des enfants du parent
				System.out.println(y.nom+" "+y.prenom+" est enregistré en tant qu'enfant de "+x.nom+" "+x.prenom);
			}else {
				/////////mêmes étapes pour une naissance avec les deux parents présents
				x = trouverCitoyen(listMairies);
				y = trouverCitoyen(listMairies);
				if(x==null) {
					System.out.println("L'identifiant du parent n'est pas enregistré, réessaiyez.");
					break;
				}
				if(y==null) {
					System.out.println("L'identifiant du parent n'est pas enregistré, réessaiyez.");
					break;
				}
				if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
					System.out.println("il s'agit de la même personne, vérifiez les identifiants");
					break;
				}
				i = x.mariage.size()-1;
				if((x.ageLegal(28)==false)||(y.ageLegal(28)==false)) {	//vérification de l'âge des futurs parents adoptifs
					System.out.println("Un des parents au moins souhaitant adopter n'a pas l'âge recquis (>28 ans).");
					break;
				}
				if (x.mariage.get(i).conjoint2 == y) {		//on vérifie que les parents adoptifs sont mariés entre eux
					System.out.println("Pour la personne adoptée : ");
					Citoyen z = trouverCitoyen(listMairies);
					if(z==null) {
						System.out.println("L'un des identifiants des parents n'est pas enregistré, réessaiyez.");
						break;
					}
					if(x.estParentDe(z)==true) { //on vérifie que l'enfant ne soit pas déjà enregistrer
						System.out.println(x.nom+" "+x.prenom+" est déjà enregistré en tant que parent de "+z.nom+" "+z.prenom);
						break;
					} else if(y.estParentDe(z)==true) {
						System.out.println(y.nom+" "+y.prenom+" est déjà enregistré en tant que parent de "+z.nom+" "+z.prenom);
						break;
					}else if((x.identifiant==z.identifiant)||(y.identifiant==z.identifiant)) { // on verifie qu'il ne s'agit pas de la même personne
						System.out.println("il s'agit de la même personne, vérifiez les identifiants");
						break;
					}
					m = x.getMairie();
					z.mairieDeResidence.listCitoyens.remove(z);
					m.listCitoyens.add(z);
					z.setMairie(m);
					z.nom = x.nomUsage;						//transmission du nom commun des parents à l'adopté
					z.nomUsage = x.nomUsage;
					x.ajoutNaissance(z.naissance);			
					y.ajoutNaissance(z.naissance);
					System.out.println(z.nom+" "+z.prenom+" est enregistré en tant qu'enfant de "+y.nom+" "+y.prenom+" et "+x.nom+" "+x.prenom+".");
			   	} else {									//les parents adoptifs ne sont pas mariés entre eux
			   		System.out.println(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+"ne sont pas mariés.");
					System.out.println("L'adoption ne peut se faire si les deux parents ne sont pas mariés.");
			   	}
			}
			break;
				
		///choix 5 : Décès///
		case 5 :				
			
			x = trouverCitoyen(listMairies);				//on cherche le citoyen décédé dans les listes de citoyen
			if(x==null) {
				System.out.println("L'identifiant du supposé défunt n'est pas enregistré, réessaiyez.");
				break;
			}
			m = x.getMairie();								//on récupère sa mairie
			m.listDeces.add((new Deces(x,dateDuJour)));		//on ajoute le décès du citoyen à la liste des décès de la mairie
			m.listCitoyens.remove(x);						//on enlève le citoyen de la liste des citoyens vivants
			x.setMort(new Deces(x,dateDuJour));				//on applique le décès à la personne décédée
			x.mort.setMairie(x.getMairie());				//on lui applique une mairie de décès qui est la même que celle de résidence
			System.out.println("Le citoyen "+x.nom+" "+x.prenom+" a été enregistré décédé le " +sdf.format(x.mort.dateDeces)+".");
			
			if (x.estMarie()==true){		//on vérifie si le défunt était marié				
				i = x.mariage.size()-1;
				Citoyen veuf = x.mariage.get(i).conjoint2;	//on récupère le conjoint du défunt
				if(veuf.nomUsage == x.nom) {				//test si c'était le conjoint qui avait pris le nom du défunt lors du mariage
					veuf.changeName(veuf,veuf.nom);			//changement du nom au nom originel pour la personne veuve
					System.out.println("Le conjoint "+x.nom+" "+x.prenom+" a repris son nom de naissance.");
					if(veuf.estVeuf()==true) {				//vérification du changement de statut
						System.out.println("Le statut du conjoint "+veuf.nom+" "+veuf.prenom+" a bien été modifié.");
					}
				}
			}
			break;
				
		///choix 6 : Affichage de l'état civil///
		case 6 :
			
			x = trouverCitoyen(listMairies);	//on cherche le citoyen à afficher
			if(x==null) {						//vérification de l'existence du citoyen
				System.out.println("L'identifiant n'est pas enregistré, réessaiyez.");
				break;
			}
			x.affichage();						//on affiche les informations du citoyen trouvé
			break;
				
		///choix 7 : Affichage de la liste des citoyens d'une mairie choisie///
		case 7 : 
			
			m = choixMairie(listMairies);
			if(m==null) {						//vérification de l'existence de la mairie recherchée		
				System.out.println("La mairie n'existe pas, veuillez réessayer.");
				break;
			}
			for (int j = 0; j<m.listCitoyens.size(); j++) {		//on parcours la liste des citoyens de la mairie d'intérêt
				m.listCitoyens.get(j).affichage();				//on affiche les citoyens un par un 
				System.out.println("");
			}
			int nbhabitant = m.listCitoyens.size();				//on affiche le nombre total d'habitants de la ville
			System.out.println("Le nombre d'habitants de la ville "+m.nomVille+" est "+nbhabitant);
			break;
				
		///choix 8 : Saisie d'un nouveau citoyen///
		case 8 :
			
			m = choixMairie(listMairies);
			if(m==null) {						//vérification de l'existence de la mairie recherchée		
				System.out.println("La mairie n'existe pas, veuillez réessayer.");
				break;
			}
			
			System.out.println("Est-ce un nouvel arrivant ? (1: oui /2: non)");
			reponse = scan.nextInt();
			if(reponse==1) {
				int id = newIdentifiant(listMairies);
				System.out.print("Nom : ");
				String nom = scan.nextLine();
				nom = scan.nextLine();
				Citoyen z = CreerCitoyen(m, id, nom); 	//on créer le citoyen à l'aide d'un constructeur dans la méthode
				z.setMairie(m);							//on lui attribue la mairie dont on a décidé au préalable
				m.listCitoyens.add(z);					//on ajoute le nouveau citoyen à la liste de la mairie choisie
			}else {
				x = trouverCitoyen(listMairies);
				if(x==null) {							//vérification de l'existence du citoyen
					System.out.println("L'identifiant du citoyen à déplacer n'est pas enregistré, réessaiyez.");
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
		ArrayList<Mairie> listMairies = new ArrayList<Mairie>();		//création de la liste des mairies
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
		ArrayList<Citoyen> listCitoyens = new ArrayList<Citoyen>();		//création de la liste des mairies
		
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
		boolean redo = true;					//booléenne initialisée à vrai
		while (redo == true){					//tant qu'elle est vraie, le menu est redemandé après l'action
			affichageMenu();					//on affiche le menu
			System.out.print("Choix : ");		//l'utilisateur fait son choix d'action
			int choix = scan.nextInt();
			
			while ((choix<1) || (choix>9)) {	//dans le cas où le choix est incorrect, l'utilisateur doit choisir à nouveau
				System.out.print("Choix incorrect. Veuillez réécrire : ");
				choix = scan.nextInt();
			}
			if (choix==9) {						
				System.out.println("Vous allez quitter le programme. \n Confirmer ? (1 : oui/2 : non)");
				int confirmation = scan.nextInt();		//on demande la confirmation de sortie du menu
				if(confirmation==1) {
					redo=false;							//booléenne fausse donc on ne retournera pas dans la boucle
					System.out.println("Au revoir !");
				}else {
					System.out.println("Vous revenez au menu.");
				}
			}
			listMairies2 = action(choix,listMairies2);			//on lance l'action à effectuer en fonction du choix de l'utilisateur
		}
		
		File file2 = new File ("fichierListMairies.txt");		//sauvegarde dans fichier annexe
		FileOutputStream fo = new FileOutputStream(file2);
		ObjectOutputStream output = new ObjectOutputStream(fo);
		for(Mairie m : listMairies2) {
			output.writeObject(m);
			System.out.println("enregistré");
		}
		output.close();
		fo.close();
	}		
}


