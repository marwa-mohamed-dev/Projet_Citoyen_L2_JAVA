package projetCitoyen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InterfaceGraphique extends JFrame {
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Date dateDuJour = new Date();	

	private JPanel contentPane;
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel Action;
	private JLabel verification_1;
	private JLabel verification_2;
	private JLabel verification_3;
	private JLabel verification_4;
	private JLabel verification_5;
	private JLabel verification_6;
	private JLabel verification_7;
	private JLabel choixSituation;
	private JButton button_Choix1;
	private JButton button_Choix2;
	private JButton button_Entrer;
	private JButton button_Entrer2;
	private JButton button_Entrer3;
	private JButton button_Redo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ArrayList<Mairie> listMairies = new ArrayList<Mairie>();		//création de la liste des mairies
		Mairie essai = new Mairie(1,"essai");
		Mairie essai2 = new Mairie(2,"essai2");
		//////////////////////////////////
		Naissance n = new Naissance(entrerDate("20/10/2000 12:55"));
		Citoyen x = new Citoyen(1,"n1","p1");
		x.setNaissance(n);
		x.setSex("f");
		Naissance n2 = new Naissance(entrerDate("05/08/2000 13:11"));
		Citoyen y = new Citoyen(2,"n2","p2");
		y.setNaissance(n2);
		y.setSex("m");
		Naissance n3 = new Naissance(entrerDate("01/03/2008 14:10"));
		Citoyen z = new Citoyen(3,"n3","p3");
		z.setNaissance(n3);
		z.setSex("f");
		
		x.setMairie(essai);
		y.setMairie(essai);
		z.setMairie(essai2);
		essai.listCitoyens.add(x);
		essai.listCitoyens.add(y);
		essai2.listCitoyens.add(z);
		listMairies.add(essai);
		listMairies.add(essai2);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique frame = new InterfaceGraphique(listMairies);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ArrayList<Mairie>  listMairies(ArrayList<Mairie> listMairies) {
		return listMairies;
	}
	/**
	 * Create the frame.
	 */
	public InterfaceGraphique(ArrayList<Mairie> listMairies) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 665, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel Bienvenue = new JLabel("Bienvenue");
		Bienvenue.setFont(new Font("Tahoma", Font.BOLD, 20));
		Bienvenue.setBounds(260, 0, 150, 50);
		panel.add(Bienvenue);
		
		JButton button_Marriage = new JButton("Mariage");
		button_Marriage.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				Action.setText("MARIAGE");
				Action.setVisible(true);
				
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("N° mairie mariage : ");
				textField_1.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblNewLabel_2.setText("Id futur conjoint 1 : ");
				textField_2.setVisible(true);
				lblNewLabel_3.setText("Id futur conjoint 2 : ");
				lblNewLabel_3.setVisible(true);
				textField_3.setVisible(true);
				button_Entrer.setVisible(true);
				verification_1.setVisible(true);
				verification_2.setVisible(true);
				verification_3.setVisible(true);
				verification_6.setVisible(true);
				button_Redo.setVisible(true);
				button_Entrer.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Mairie m = choixMairie(listMairies, verification_1, textField_1);
						Citoyen x = trouverCitoyen(listMairies, verification_2,verification_6, textField_2);
						Citoyen y = trouverCitoyen(listMairies, verification_3,verification_6, textField_3);
						if(m==null) {						
							verification_1.setText("La mairie n'existe pas");
						} else if(x==null) {						
							verification_2.setText("L'identifiant n'exite pas");
						} else if(y==null) {						
							verification_3.setText("L'identifiant n'exite pas");
						} else if(x.estParentDe(y)==true) { //on vérifie que l'enfant ne soit pas déjà enregistrer
							verification_6.setText(x.nom+" "+x.prenom+" est enregistré en tant que parent de "+y.nom+" "+y.prenom);
						}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
							verification_6.setText("il s'agit de la même personne, vérifiez les identifiants");
						} 
						else if(x.estMarie() == true) {			//vérification pour les deux futurs mariés si ils ne le sont pas déjà
							verification_2.setText(x.nom+" "+x.prenom+" est déjà marié.");
							
						} else if(y.estMarie() == true) {
							verification_3.setText(y.nom+" "+y.prenom+" est déjà marié.");
							
						} else 	if(x.ageLegal(18) == false) {	//vérification pour les deux futurs mariés si ils sont majeurs
							verification_6.setText(x.nom+" "+x.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
							
						}
						else if(y.ageLegal(18) == false) {
							verification_6.setText(y.nom+" "+y.prenom+" n'est pas majeur, le mariage ne peut avoir lieu.");
							
							
						/////////Fin des vérifications préalables, on peut procéder au mariage
						}else{
							
							choixSituation.setText("Nom de famille ?");
							choixSituation.setVisible(true);
							button_Choix1.setText(x.nom);
							button_Choix1.setVisible(true);
							button_Choix2.setText(y.nom);
							button_Choix2.setVisible(true);
							m.listMariages.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste de la mairie
							
							/////////procédure pour le premier conjoint
							x.mariage.add((new Mariage(x,y,dateDuJour)));	//ajout du mariage dans la liste personnelle du citoyen
							int i = x.mariage.size()-1;						//on se place dans le dernier mariage du citoyen
							x.mariage.get(i).setMairie(m);					//association de la mairie au mariage tout juste créé
							
							////////même procédure pour le deuxième conjoint
							y.mariage.add((new Mariage(y,x,dateDuJour)));	//le citoyen concerné est toujours le conjoint1
							i = y.mariage.size()-1;
							y.mariage.get(i).setMairie(m);
							
							choixSituation.setText("Saisissez le choix du nom de famille commun du couple"); //choix du nom de famille
							choixNom(x,y, button_Choix1,button_Choix2);
							verification_6.setText(x.nomUsage +" "+ x.prenom+" et " +y.nomUsage+" "+y.prenom+" sont maintenant mariés dans la mairie de "+m.nomVille+".");
							button_Choix1.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									lblNewLabel_4.setVisible(false);
									textField_4.setVisible(false);
									verification_4.setVisible(false);
									lblNewLabel_5.setVisible(false);
									textField_5.setVisible(false);
									verification_5.setVisible(false);
									lblNewLabel_7.setVisible(false);
									textField_7.setVisible(false);
									verification_7.setVisible(false);
									verification_6.setText("Le nom de famille choisi est :"+x.nomUsage+ ".");
								}
							});
							button_Choix1.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									lblNewLabel_4.setVisible(false);
									textField_4.setVisible(false);
									verification_4.setVisible(false);
									lblNewLabel_5.setVisible(false);
									textField_5.setVisible(false);
									verification_5.setVisible(false);
									lblNewLabel_7.setVisible(false);
									textField_7.setVisible(false);
									verification_7.setVisible(false);
									verification_6.setText("Le nom de famille choisi est :"+x.nomUsage+ ".");
								}
							});
						}
					}
				});
			}
		});
		button_Marriage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Marriage.setBounds(2, 50, 100, 40);
		panel.add(button_Marriage);
		
		JButton button_Divorce = new JButton("Divorce");
		button_Divorce.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				button_Redo.setVisible(true);
				Action.setText("DIVORCE");
				Action.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("Id futur ancient conjoint 1 : ");
				textField_1.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblNewLabel_2.setText("Id futur ancient conjoint 2 : ");
				textField_2.setVisible(true);
				button_Entrer.setVisible(true);
				verification_1.setVisible(true);
				verification_2.setVisible(true);
				verification_6.setVisible(true);
				button_Entrer.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						
						Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
						
						Citoyen y = trouverCitoyen(listMairies, verification_2,verification_6, textField_2);
						if(x==null) {						
							verification_1.setText("L'identifiant n'exite pas");
						} else if(y==null) {						
							verification_2.setText("L'identifiant n'exite pas");
						} else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
							verification_6.setText("il s'agit de la même personne, vérifiez les identifiants");
						} 
						else if ((x.mariage.isEmpty())||(y.mariage.isEmpty())) {	//si l'un des deux n'a jamais été marié, pas de divorce possible
							verification_6.setText(x.nom +" "+ x.prenom+"et" +y.nom +" "+ y.prenom+" ne sont pas mariés.");
							
						}
						//////////après les vérifications de base, on effectue le divorce
						else {
							int i = x.mariage.size()-1;
							if (x.mariage.get(i).conjoint2 == y) {			//on vérifie qu'ils sont bien mariés l'un à l'autre
					    		x.mariage.get(i).setDivorce(dateDuJour);	//on applique la date du jour en tant que date du divorce
								i = y.mariage.size()-1;
								y.mariage.get(i).setDivorce(dateDuJour);	//on fait de même pour le deuxième citoyen
								repriseNomInitial(x,y);						//le citoyen ayant changé de nom d'usage reprend son nom d'origine
								verification_6.setText(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" sont maintenant divorcés.");
								
						   	} else {										//dans le cas où ils sont mariés mais pas l'un à l'autre
						   		verification_6.setText(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+" ne sont pas mariés entre eux.");
								
						   	}
						
						}
					}
				});
			}
		});
		button_Divorce.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Divorce.setBounds(125, 50, 100, 40);
		panel.add(button_Divorce);
		
		JButton button_Naissance = new JButton("Naissance");
		button_Naissance.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				Action.setText("NAISSANCE");
				Action.setVisible(true);
				choixSituation.setText("Situation monoparentale ?");
				choixSituation.setVisible(true);
				button_Choix1.setVisible(true);
				button_Choix1.setText("oui");
				button_Choix2.setVisible(true);
				button_Choix2.setText("non");
				button_Redo.setVisible(true);
				button_Choix1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						lblNewLabel_7.setVisible(false);
						textField_7.setVisible(false);
						lblNewLabel_7.setVisible(false);
						
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("N° mairie naissance : ");
						textField_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_2.setText("Id parent : ");
						textField_2.setVisible(true);
						button_Entrer.setVisible(true);
						verification_1.setVisible(true);
						verification_2.setVisible(true);
						verification_3.setVisible(true);
						verification_4.setVisible(true);
						verification_5.setVisible(true);
						lblNewLabel_3.setVisible(true);
						lblNewLabel_3.setText("prenom : ");
						textField_3.setVisible(true);
						lblNewLabel_4.setVisible(true);
						lblNewLabel_4.setText("date de naissance : ");
						textField_4.setVisible(true);
						lblNewLabel_5.setVisible(true);
						lblNewLabel_5.setText("sexe (f ou m) : ");
						textField_5.setVisible(true);
						verification_6.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Mairie m = choixMairie(listMairies, verification_1, textField_1);
								Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
								if(m==null) {						
									verification_1.setText("La mairie n'existe pas");
								} else if(x==null) {						
									verification_2.setText("L'identifiant n'exite pas");
								} else {
									int id = newIdentifiant(listMairies);		//on récupère un nouvel identifiant national
									String nomEnfant = x.nomUsage;				//le nom de l'enfant est le nom d'usage du parent unique
									String strDate = textField_4.getText();
									if (isDateValide(strDate)==false) {
										verification_4.setText("bon format jj/MM/aaaa hh:mm.");
									} else {
										Date dateNaissance = entrerDate(strDate);	//on associe la date rentrée à la date de naissance
										Naissance n = new Naissance(x,dateNaissance);	//création d'une naissance			
										String prenom = recupString(textField_3); //on enregistre le prénom
										Citoyen z = new Citoyen (id,nomEnfant,prenom);	//le nouveau-né est créé
										z.setNaissance(n);							//on lui applique la naissance créée
										String sexe = recupString(textField_5);
										z.setSex(sexe);								//on donne le sexe choisi au citoyen
										z.setMairie(m);	
										m.listNaissances.add(new Naissance(x,z.naissance.dateNaissance)); //on ajoute cette naissance à la liste des naissances de la mairie
										z.naissance.setMairie(m);					//on donne une mairie à la naissance
										m.listCitoyens.add(z);						//on ajoute le nouveau-né à la liste des citoyens de la mairie de naissance
										verification_6.setText("Le nouveau-né a bien été enregistré auprès de la mairie.");
										x.ajoutNaissance(z.naissance);				//ajout du nouveau-né dans la liste des enfants du parent
										
									}
								}
								
							}
						});
					}
				});
				
				button_Choix2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {	
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("N° mairie naissance : ");
						textField_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_2.setText("Id parent 1 : ");
						textField_2.setVisible(true);
						button_Entrer.setBounds(200, 435, 211, 21);
						button_Entrer.setVisible(true);
						verification_1.setVisible(true);
						verification_2.setVisible(true);
						verification_3.setVisible(true);
						verification_4.setVisible(true);
						verification_5.setVisible(true);
						verification_7.setVisible(true);
						lblNewLabel_3.setVisible(true);
						lblNewLabel_3.setText("Id parent 2 : ");
						textField_3.setVisible(true);
						lblNewLabel_4.setVisible(true);
						lblNewLabel_4.setText("prenom : ");
						textField_4.setVisible(true);
						lblNewLabel_5.setVisible(true);
						lblNewLabel_5.setText("date de naissance : ");
						textField_5.setVisible(true);
						lblNewLabel_7.setVisible(true);
						lblNewLabel_7.setText("sexe (f ou m) : ");
						textField_7.setVisible(true);
						verification_6.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Mairie m = choixMairie(listMairies, verification_1, textField_1);
								Citoyen x = trouverCitoyen(listMairies, verification_2,verification_6, textField_2);
								Citoyen y = trouverCitoyen(listMairies, verification_3,verification_3, textField_3);
								if(m==null) {						
									verification_1.setText("La mairie n'existe pas");
								} else if(x==null) {						
									verification_1.setText("L'identifiant n'exite pas");
								} else if(y==null) {						
									verification_2.setText("L'identifiant n'exite pas");
								} else if((x.identifiant==y.identifiant)) { // on verifie qu'il ne s'agit pas de la même personne
									verification_6.setText("il s'agit de la même personne, vérifiez les identifiants");
									
								} else if ((x.sexe=="f"&&y.sexe=="m")||(x.sexe=="m"&&y.sexe=="f")) { //on vérifie que les sexes soient biologiquement compatibles (homme/femme)
									int id = newIdentifiant(listMairies);		//on récupère un nouvel identifiant national
									String nomEnfant = x.nomUsage;
									String strDate = textField_5.getText();
									if (isDateValide(strDate)==false) {
										verification_5.setText("bon format jj/MM/aaaa hh:mm.");
									} else {
										Date dateNaissance = entrerDate(strDate);	//on associe la date rentrée à la date de naissance
										Naissance n = new Naissance(x, y, dateNaissance);	//création d'une naissance			
										String prenom = recupString(textField_4); //on enregistre le prénom
										Citoyen z = new Citoyen (id,nomEnfant,prenom);	//le nouveau-né est créé
										z.setNaissance(n);							//on lui applique la naissance créée
										String sexe = recupString(textField_7);
										z.setSex(sexe);								//on donne le sexe choisi au citoyen
										z.setMairie(m);	
										m.listNaissances.add(n); //on ajoute cette naissance à la liste des naissances de la mairie
										z.naissance.setMairie(m);					//on donne une mairie à la naissance
										m.listCitoyens.add(z);						//on ajoute le nouveau-né à la liste des citoyens de la mairie de naissance
										verification_6.setText("Le nouveau-né a bien été enregistré auprès de la mairie.");
										x.ajoutNaissance(z.naissance);				//ajout du nouveau-né dans la liste des enfants des parents
										y.ajoutNaissance(z.naissance);
									}
								} else {
									verification_6.setText("La naissance ne peut être enregistrée, les géniteurs sont de même sexe.");
								}
							}
							
						});
						
					}
				});
				
			}
		});
		button_Naissance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		button_Naissance.setBounds(250, 50, 100, 40);
		panel.add(button_Naissance);
		
		JButton button_Adoption = new JButton("Adoption");
		button_Adoption.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Action.setText("ADOPTION");
				Action.setVisible(true);
				choixSituation.setText("Situation monoparentale ?");
				choixSituation.setVisible(true);
				button_Choix1.setVisible(true);
				button_Choix2.setVisible(true);
				button_Redo.setVisible(true);
				button_Choix1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						verification_3.setVisible(false);
						verification_4.setVisible(false);
						verification_5.setVisible(false);
						lblNewLabel_3.setVisible(false);
						textField_3.setVisible(false);
						lblNewLabel_4.setVisible(false);
						textField_4.setVisible(false);
						lblNewLabel_5.setVisible(false);
						textField_5.setVisible(false);
						
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("Id parent : ");
						textField_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_2.setText("Id enfant adopté : ");
						textField_2.setVisible(true);
						button_Entrer.setVisible(true);
						verification_1.setVisible(true);
						verification_2.setVisible(true);
						verification_6.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
								Citoyen y = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);		//on cherche la personne qui va être adoptée
								if(x==null) {						
									verification_1.setText("L'identifiant n'exite pas");
								} else if(y==null) {							//on vérifie si elle existe bien
									verification_2.setText("L'identifiant n'exite pas");
										
								} else if(x.estParentDe(y)==true) {
									verification_6.setText(y.nom+" "+y.prenom+" est déjà enregistré en tant que parent de "+x.nom+" "+x.prenom);
								} else if((x.ageLegal(28)==false)) {	//vérification de l'âge des futurs parents adoptifs
									verification_6.setText("Un des parents au moins souhaitant adopter n'a pas l'âge recquis (>28 ans).");
								} else {
									Mairie m = x.getMairie();						//on récupère la mairie du parent
									y.mairieDeResidence.listCitoyens.remove(y);	//on retire le citoyen qui va être adopté de sa mairie actuelle
									m.listCitoyens.add(y);					//on l'ajoute ensuite à la liste de citoyens de la mairie du parent adoptif
									y.setMairie(m);							//on attribue la mairie du parent au citoyen adopté
									y.nom = x.nomUsage;						//le citoyen adopté prend le nom de son parent adoptif
									y.nomUsage = x.nomUsage;				//son nom et son nom d'usage sont modifiés
									x.ajoutNaissance(y.naissance);			//on ajoute le citoyen adopté dans la liste des enfants du parent
									verification_6.setText(y.nom+" "+y.prenom+" est enregistré en tant qu'enfant de "+x.nom+" "+x.prenom);
								}
								
							}
								
							
						});
					}
				});
				
				button_Choix2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						verification_4.setVisible(false);
						verification_5.setVisible(false);
						verification_7.setVisible(false);
						lblNewLabel_7.setVisible(false);
						textField_7.setVisible(false);
						lblNewLabel_4.setVisible(false);
						textField_4.setVisible(false);
						lblNewLabel_5.setVisible(false);
						textField_5.setVisible(false);
						
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("Id parent 1 : ");
						textField_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_2.setText("Id parent 2 : ");
						textField_2.setVisible(true);
						button_Entrer.setVisible(true);
						verification_1.setVisible(true);
						verification_2.setVisible(true);
						verification_3.setVisible(true);
						lblNewLabel_3.setVisible(true);
						lblNewLabel_3.setText("Id enfant adopté : ");
						textField_3.setVisible(true);
						verification_6.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
								Citoyen y = trouverCitoyen(listMairies, verification_2,verification_6, textField_2);
								Citoyen z = trouverCitoyen(listMairies, verification_3,verification_6, textField_3);
								if(x==null) {						
									verification_1.setText("L'identifiant n'exite pas");
								} else if(y==null) {						
									verification_2.setText("L'identifiant n'exite pas");
								}else if(x.identifiant==y.identifiant) { // on verifie qu'il ne s'agit pas de la même personne
									verification_6.setText("il s'agit de la même personne, vérifiez les identifiants");
								} else if((x.ageLegal(28)==false)||(y.ageLegal(28)==false)) {	//vérification de l'âge des futurs parents adoptifs
									verification_6.setText("Un des parents au moins souhaitant adopter n'a pas l'âge recquis (>28 ans).");
								} else {
									int i = x.mariage.size()-1;
									if (x.mariage.get(i).conjoint2 == y) {		//on vérifie que les parents adoptifs sont mariés entre eux
										if(z==null) {
											verification_6.setText("L'un des identifiants des parents n'est pas enregistré, réessaiyez.");
											
										}
										if(x.estParentDe(z)==true) { //on vérifie que l'enfant ne soit pas déjà enregistrer
											verification_6.setText(x.nom+" "+x.prenom+" est déjà enregistré en tant que parent de "+z.nom+" "+z.prenom);
											
										} else if(y.estParentDe(z)==true) {
											verification_6.setText(y.nom+" "+y.prenom+" est déjà enregistré en tant que parent de "+z.nom+" "+z.prenom);
										}else if((x.identifiant==z.identifiant)||(y.identifiant==z.identifiant)) { // on verifie qu'il ne s'agit pas de la même personne
											verification_6.setText("il s'agit de la même personne, vérifiez les identifiants");
											
										} else {
										Mairie m = x.mairieDeResidence;
										m = x.getMairie();
										z.mairieDeResidence.listCitoyens.remove(z);
										m.listCitoyens.add(z);
										z.setMairie(m);
										z.nom = x.nomUsage;						//transmission du nom commun des parents à l'adopté
										z.nomUsage = x.nomUsage;
										x.ajoutNaissance(z.naissance);			
										y.ajoutNaissance(z.naissance);
										verification_6.setText(z.nom+" "+z.prenom+" est maintenant enregistré en tant qu'enfant de "+y.nom+" "+y.prenom+" et "+x.nom+" "+x.prenom+".");
										}
								   	} else {									//les parents adoptifs ne sont pas mariés entre eux
								   		verification_6.setText(x.nom +" "+ x.prenom+" et " +y.nom +" "+ y.prenom+"ne sont pas mariés.\n L'adoption ne peut se faire si les deux parents ne sont pas mariés.");
								   	}	
								}
								
							}
							
						});
						
					}
				});
				
			}	
			
		});
		button_Adoption.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Adoption.setBounds(365, 50, 100, 40);
		panel.add(button_Adoption);
		
		JButton button_Deces = new JButton("Deces");
		button_Deces.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e1) {
				button_Redo.setVisible(true);
				Action.setText("DECES");
				Action.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("Id personne décédé : ");
				textField_1.setVisible(true);
				button_Entrer.setVisible(true);
				verification_1.setVisible(true);
				verification_6.setVisible(true);
				button_Entrer.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
						if(x==null) {						
							verification_1.setText("L'identifiant n'exite pas");
						} else {
							Mairie m = x.getMairie();								//on récupère sa mairie
							m.listDeces.add((new Deces(x,dateDuJour)));		//on ajoute le décès du citoyen à la liste des décès de la mairie
							m.listCitoyens.remove(x);						//on enlève le citoyen de la liste des citoyens vivants
							x.setMort(new Deces(x,dateDuJour));				//on applique le décès à la personne décédée
							x.mort.setMairie(x.getMairie());				//on lui applique une mairie de décès qui est la même que celle de résidence
							verification_6.setText("Le citoyen "+x.nom+" "+x.prenom+" a été enregistré décédé le " +sdf.format(x.mort.dateDeces)+".");
							
							if (x.estMarie()==true){		//on vérifie si le défunt était marié				
								int i = x.mariage.size()-1;
								Citoyen veuf = x.mariage.get(i).conjoint2;	//on récupère le conjoint du défunt
								if(veuf.nomUsage == x.nom) {				//test si c'était le conjoint qui avait pris le nom du défunt lors du mariage
									veuf.changeName(veuf,veuf.nom);			//changement du nom au nom originel pour la personne veuve
									verification_6.setText("Le citoyen "+x.nom+" "+x.prenom+" a été enregistré décédé le " +sdf.format(x.mort.dateDeces)+". \n Le conjoint "+x.nom+" "+x.prenom+" a repris son nom de naissance et son etat a changé.");
								}
							}
						}
					}
				});
			}
		});
		button_Deces.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Deces.setBounds(500, 50, 100, 40);
		panel.add(button_Deces);
		
		JButton button_EtatCivil = new JButton("Etat Civil");
		button_EtatCivil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e1) {
				button_Redo.setVisible(true);
				Action.setText("ETAT CIVIL");
				Action.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("Id citoyen : ");
				textField_1.setVisible(true);
				button_Entrer2.setVisible(true);
				verification_1.setVisible(true);
				verification_6.setVisible(true);
				button_Entrer2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
						if(x==null) {						
							verification_1.setText("L'identifiant n'exite pas");
						} else {
							if((x.estMarie()==true)&&(x.getSex()=="f")){
								int i = x.mariage.size()-1;
								JOptionPane.showMessageDialog(null,(x.nom +", épouse"+x.nomUsage+"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Marié à \n"+x.mariage.get(i).conjoint2.nom +"\n"+x.mariage.get(i).conjoint2.prenom));
							}else if((x.estMarie()==true)&&(x.getSex()=="m")){
								int i = x.mariage.size()-1;
								JOptionPane.showMessageDialog(null,(x.nom +", époux"+x.nomUsage+"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Mariée à \n"+x.mariage.get(i).conjoint2.nom +"\n"+x.mariage.get(i).conjoint2.prenom));
							}else if((x.estDivorce()==true)&&(x.getSex()=="m")){
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Divorcé)"));
							}else if((x.estDivorce()==true)&&(x.getSex()=="f")){
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Divorcée)"));
							}else if((x.estVeuf()==true)&&(x.getSex()=="m")){
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Veuf)"));
							}else if((x.estVeuf()==true)&&(x.getSex()=="f")){
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Veuve)"));
							}else if(x.estMort()==true){
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"citoyen décédé"));
							}else {
								JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire"));
								
							}
						}
					}
				});
			}
		});
		button_EtatCivil.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_EtatCivil.setBounds(50, 100, 100, 40);
		panel.add(button_EtatCivil);
		
		JButton button_Affichage = new JButton("Affichage de la liste des citoyens");
		button_Affichage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e1) {
				button_Redo.setVisible(true);
				Action.setText("AFFICHAGE");
				Action.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("N° mairie : ");
				textField_1.setVisible(true);
				button_Entrer3.setVisible(true);
				verification_1.setVisible(true);
				verification_6.setVisible(true);
				button_Entrer3.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Mairie m = choixMairie(listMairies, verification_1, textField_1);
						if(m==null) {						
							verification_1.setText("La mairie n'existe pas");
						} else {
							for (int j = 0; j<m.listCitoyens.size(); j++) {		//on parcours la liste des citoyens de la mairie d'intérêt
								//on affiche les citoyens un par un 
								Citoyen x =m.listCitoyens.get(j);
								if((x.estMarie()==true)&&(x.getSex()=="f")){
									int i = x.mariage.size()-1;
									JOptionPane.showMessageDialog(null,(x.nom +", épouse"+x.nomUsage+"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Marié à \n"+x.mariage.get(i).conjoint2.nom +"\n"+x.mariage.get(i).conjoint2.prenom));
								}else if((x.estMarie()==true)&&(x.getSex()=="m")){
									int i = x.mariage.size()-1;
									JOptionPane.showMessageDialog(null,(x.nom +", époux"+x.nomUsage+"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Mariée à \n"+x.mariage.get(i).conjoint2.nom +"\n"+x.mariage.get(i).conjoint2.prenom));
								}else if((x.estDivorce()==true)&&(x.getSex()=="m")){
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Divorcé)"));
								}else if((x.estDivorce()==true)&&(x.getSex()=="f")){
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Divorcée)"));
								}else if((x.estVeuf()==true)&&(x.getSex()=="m")){
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Veuf)"));
								}else if((x.estVeuf()==true)&&(x.getSex()=="f")){
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire (Veuve)"));
								}else if(x.estMort()==true){
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"citoyen décédé"));
								}else {
									JOptionPane.showMessageDialog(null,(x.nom +"\n"+ x.prenom +"\n"+ x.naissance.dateNaissance+"\n"+x.getSex()+"\n"+"Célibataire"));
									
								}
							}
							int nbhabitant = m.listCitoyens.size();				//on affiche le nombre total d'habitants de la ville
							verification_6.setText("Le nombre d'habitants de la ville "+m.nomVille+" est : "+nbhabitant);
							
						}
						
					}
				});
				
			}
		});
		button_Affichage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Affichage.setBounds(180, 100, 210, 40);
		panel.add(button_Affichage);
		
		JButton button_NCitoyen = new JButton("Nouveau Citoyen");
		button_NCitoyen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e1) {
				Action.setText("NOUVEAU CITOYEN");
				Action.setVisible(true);
				choixSituation.setText("nouvelle arrivant ?");
				choixSituation.setVisible(true);
				button_Choix1.setText("oui");
				button_Choix1.setVisible(true);
				button_Choix2.setVisible(true);
				button_Choix2.setText("non");
				button_Redo.setVisible(true);
				button_Choix1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						lblNewLabel_7.setVisible(false);
						textField_7.setVisible(false);
						lblNewLabel_7.setVisible(false);
						
						lblNewLabel_1.setVisible(true);
						lblNewLabel_1.setText("N° mairie : ");
						textField_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_2.setText("nom : ");
						textField_2.setVisible(true);
						button_Entrer.setVisible(true);
						verification_1.setVisible(true);
						verification_2.setVisible(true);
						verification_3.setVisible(true);
						verification_4.setVisible(true);
						verification_5.setVisible(true);
						lblNewLabel_3.setVisible(true);
						lblNewLabel_3.setText("prenom : ");
						textField_3.setVisible(true);
						lblNewLabel_4.setVisible(true);
						lblNewLabel_4.setText("date de naissance : ");
						textField_4.setVisible(true);
						lblNewLabel_5.setVisible(true);
						lblNewLabel_5.setText("sexe (f ou m) : ");
						textField_5.setVisible(true);
						verification_6.setVisible(true);
						
						button_Entrer.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Mairie m = choixMairie(listMairies, verification_1, textField_1);
								if(m==null) {						
									verification_1.setText("La mairie n'existe pas");
								} else {
									int id = newIdentifiant(listMairies);		//on récupère un nouvel identifiant national
									String nom = recupString(textField_2);				
									String strDate = textField_4.getText();
									if (isDateValide(strDate)==false) {
										verification_4.setText("bon format jj/MM/aaaa hh:mm.");
									} else {
										Date dateNaissance = entrerDate(strDate);	
										Naissance n = new Naissance(dateNaissance);			
										String prenom = recupString(textField_3); 
										Citoyen z = new Citoyen (id,nom,prenom);	
										z.setNaissance(n);							
										String sexe = recupString(textField_4);
										z.setSex(sexe);								
										z.setMairie(m);	
										m.listNaissances.add(n);
										z.naissance.setMairie(m);					
										m.listCitoyens.add(z);						
										verification_6.setText("Le citoyen a bien été enregistré auprès de la mairie.");
									}
								}
								
							}
								
							
						});
					}
				});
				button_Choix2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						verification_3.setVisible(false);
						verification_4.setVisible(false);
						verification_5.setVisible(false);
						lblNewLabel_3.setVisible(false);
						textField_3.setVisible(false);
						lblNewLabel_4.setVisible(false);
						textField_4.setVisible(false);
						lblNewLabel_5.setVisible(false);
						textField_5.setVisible(false);
						verification_1.setVisible(false);
						verification_2.setVisible(false);
						verification_6.setVisible(false);
						lblNewLabel_2.setVisible(false);
						textField_2.setVisible(false);
						
						lblNewLabel_1.setVisible(true);
						textField_1.setVisible(true);
						
						button_Entrer.setVisible(true);
						button_Entrer.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								Mairie m = choixMairie(listMairies, verification_1, textField_1);
								Citoyen x = trouverCitoyen(listMairies, verification_1,verification_6, textField_1);
								if(m==null) {						
									verification_1.setText("La mairie n'existe pas");
								} if(x==null) {						
									verification_1.setText("L'identifiant n'exite pas");
								}else {
									x.getMairie().listCitoyens.remove(x);
									x.setMairie(m);
									m.listCitoyens.add(x);
								}
								
							}
						});
					}
				});
				
			}
		});
		button_NCitoyen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_NCitoyen.setBounds(420, 100, 129, 40);
		panel.add(button_NCitoyen);
		
		JButton button_Quitter = new JButton("Quitter");
		button_Quitter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e1) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		button_Quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Quitter.setBounds(255, 150, 100, 40);
		panel.add(button_Quitter);
		
		Action = new JLabel("New label");
		Action.setHorizontalAlignment(SwingConstants.CENTER);
		Action.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Action.setBounds(200, 200, 210, 13);
		panel.add(Action);
		Action.setVisible(false);
		
		choixSituation = new JLabel("New label");
		choixSituation.setHorizontalAlignment(SwingConstants.CENTER);
		choixSituation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		choixSituation.setBounds(150, 225, 300, 13);
		panel.add(choixSituation);
		choixSituation.setVisible(false);
		
		button_Choix1 = new JButton("oui");
		button_Choix1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Choix1.setBounds(190, 248, 100, 21);
		panel.add(button_Choix1);
		button_Choix1.setVisible(false);
		
		button_Choix2 = new JButton("non");
		button_Choix2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Choix2.setBounds(300, 248, 100, 21);
		panel.add(button_Choix2);
		button_Choix2.setVisible(false);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(125, 285, 250, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(314, 285, 96, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);

		verification_1 = new JLabel("");
		verification_1.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_1.setBounds(415, 285, 272, 21);
		panel.add(verification_1);
		verification_1.setVisible(false);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(125, 310, 177, 13);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setBounds(314, 310, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);

		verification_2 = new JLabel("");
		verification_2.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_2.setBounds(415, 310, 272, 21);
		panel.add(verification_2);
		verification_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(125, 335, 177, 13);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(314, 335, 96, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setVisible(false);

		verification_3 = new JLabel("");
		verification_3.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_3.setBounds(415, 335, 272, 21);
		panel.add(verification_3);
		verification_3.setVisible(false);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(125, 360, 177, 13);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBounds(314, 360, 96, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setVisible(false);

		verification_4 = new JLabel("");
		verification_4.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_4.setBounds(415, 360, 272, 21);
		panel.add(verification_4);
		verification_4.setVisible(false);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(125, 385, 177, 13);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setBounds(314, 385, 96, 19);
		panel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setVisible(false);
		
		verification_5 = new JLabel("");
		verification_5.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_5.setBounds(415, 385, 272, 21);
		panel.add(verification_5);
		verification_5.setVisible(false);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(125, 410, 177, 13);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_7.setBounds(314, 410, 96, 19);
		panel.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setVisible(false);
		
		verification_7 = new JLabel("");
		verification_7.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_7.setBounds(415, 410, 272, 21);
		panel.add(verification_7);
		verification_7.setVisible(false);
		
		button_Entrer = new JButton("Entrer les informations");
		button_Entrer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Entrer.setBounds(200, 433, 211, 21);
		panel.add(button_Entrer);
		button_Entrer.setVisible(false);
		
		verification_6 = new JLabel("...............................................................................................................");
		verification_6.setFont(new Font("Tahoma", Font.PLAIN,12));
		verification_6.setBounds(90, 452, 410, 21);
		panel.add(verification_6);
		verification_6.setVisible(false);
		
		
		
		button_Redo = new JButton("Nouvelle opération");
		button_Redo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField_1.setVisible(false);
				textField_1.setText("");
				textField_2.setVisible(false);
				textField_2.setText("");
				textField_3.setVisible(false);
				textField_3.setText("");
				textField_4.setVisible(false);
				textField_4.setText("");
				textField_5.setVisible(false);
				textField_5.setText("");
				textField_7.setVisible(false);
				textField_7.setText("");
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				lblNewLabel_5.setVisible(false);
				lblNewLabel_7.setVisible(false);
				Action.setVisible(false);
				verification_1.setText("");
				verification_2.setText("");
				verification_3.setText("");
				verification_4.setText("");
				verification_5.setText("");
				verification_7.setText("");
				verification_6.setText("...............................................................................................................");
				verification_1.setVisible(false);
				verification_2.setVisible(false);
				verification_3.setVisible(false);
				verification_4.setVisible(false);
				verification_5.setVisible(false);
				verification_6.setVisible(false);
				verification_7.setVisible(false);
				choixSituation.setVisible(false);
				button_Choix1.setVisible(false);
				button_Choix2.setVisible(false);
				button_Entrer.setVisible(false);
				button_Entrer2.setVisible(false);
				button_Entrer3.setVisible(false);
				button_Redo.setVisible(false);
			}
		});
		button_Redo.setBounds(199, 475, 211, 21);
		panel.add(button_Redo);
		button_Redo.setVisible(false);
		
		button_Entrer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Entrer.setBounds(199, 419, 211, 21);
		panel.add(button_Entrer);
		button_Entrer.setVisible(false);
		
		button_Entrer2 = new JButton("Entrer les informations");
		button_Entrer2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Entrer2.setBounds(200, 419, 211, 21);
		panel.add(button_Entrer2);
		button_Entrer2.setVisible(false);
		
		button_Entrer3 = new JButton("Entrer les informations");
		button_Entrer3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Entrer3.setBounds(200, 419, 211, 21);
		panel.add(button_Entrer3);
		button_Entrer3.setVisible(false);
		
	}
	
	public String recupString(JTextField textField) {
		String s = String.valueOf(textField.getText());
		return s;
	}
	
	public Mairie choixMairie(ArrayList<Mairie> listMairies, JLabel verification, JTextField textField_1) {	//permet d'indiquer dans quelle mairie on se place
		try {
			int numMairie = Integer.parseInt(textField_1.getText());
			for (int i = 0; i<listMairies.size(); i++) {				//on cherche dans la liste des mairies
				if (numMairie == listMairies.get(i).numVille) {			//si les numéros de ville coincident,
					Mairie m= listMairies.get(i);						//on récupère les informations de la mairie d'intérêt
					return m;											//la méthode retourne ces informations
				} 
			}
		} catch (NumberFormatException e1) {
			//show_validation_here.setText("invalid number");
			verification.setText("Attention ! Vous devez saisir le numero de la mairie");
		}
		return null;												//si la mairie visée n'existe pas, on ne renvoie rien
		
	}	
	
	public boolean isDateValide(String strDate) {
		try {
			sdf.setLenient(false);
			sdf.parse(strDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	public static Date entrerDate(String strDate) {
		try {
			Date d = sdf.parse(strDate);
			return d;
		} catch (ParseException e) {
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
	
	public static Citoyen trouverCitoyen(ArrayList<Mairie> listMairies, JLabel verification,JLabel verification_6, JTextField textField) {	 //permet de trouver le citoyen d'intérêt
		try {
			int id = Integer.parseInt(textField.getText());
			for (int i = 0; i<listMairies.size(); i++) {						 //on le cherche dans toutes les mairies
				for (int j = 0; j<listMairies.get(i).listCitoyens.size(); j++) { //et donc toutes les listes de citoyens
					if (id == listMairies.get(i).listCitoyens.get(j).identifiant) {
						Citoyen x = listMairies.get(i).listCitoyens.get(j);		//si on trouve l'identifiant recherché
						return x;	//on renvoie les informations du citoyen dont l'identifiant est égal à celui demandé
					} 
				}
			}
			
		} catch (NumberFormatException e1) {
			//show_validation_here.setText("invalid number");
			verification_6.setText("Attention ! L'identifiant est composé seulement de chiffre");
		}
		
		return null;		//si la recherche n'aboutie pas, on renvoie null
	}
	
	public static void choixNom(Citoyen x, Citoyen y, JButton button_Choix1, JButton button_Choix2) {	//choix d'un nom commun (mariage ou enfant)
		button_Choix1.setText("1 : "+x.nom);
		button_Choix2.setText("2 : "+y.nom);
		button_Choix1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {					
				y.changeName(y, x.nom);
			}
		});
		
		button_Choix2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {					
				x.changeName(x, y.nom);
			}
		});
		
	}
	
	public static void repriseNomInitial(Citoyen x, Citoyen y) { //après divorce ou décès du conjoint
		if(x.nomUsage == y.nom) {			//on recherche le nom intial en comparant les nom et nom d'usage  
			x.nomUsage=x.nom;
		} else if (y.nomUsage == x.nom) {	//si le nom d'usage de l'un est égal au nom de l'autre
			y.nomUsage=y.nom;				//alors la personne au nom d'usage reprend son nom 
		}
	}
	
	
	
}
