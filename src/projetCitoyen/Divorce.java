package projetCitoyen;
import java.io.Serializable;
import java.util.*;

public class Divorce implements Serializable {
	
	//Attributs de la classe Divorce
    public Date dateDivorce;

    public Mariage marriage;			//lien avec le marriage, en effet un divorce ne peut exister sans un mariage préalable
    
    //Constructeur de la classe Divorce
    public Divorce(Date date) {	
    	dateDivorce =date;				//applique une date au divorce
    }
}