package aos.project;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Dorian Davi
 *
 */

@XmlRootElement(name = "conducteur")
public class Conducteur implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int i = 1;
	private int id;
	private String name;
	private int nbtrajet; // le nombre de trajet total auquel l'user a participe
	private int nbtrajetconduit; // le nombre de trajet pour lequel l'user a conduit
	
	public Conducteur(String name, int id){
		this.id = id;
		this.name = name;
		this.nbtrajet = 0;
		this.nbtrajetconduit = 0;
	}
	
	public Conducteur(String name){
		this.id = Conducteur.i;
		Conducteur.i++;
		this.name = name;
		this.nbtrajet = 0;
		this.nbtrajetconduit = 0;
	}
	
	public int getId() {
       return id;
    }

    @XmlElement
    public void setId(int id) {
       this.id = id;
    }
    
    public String getName() {
       return name;
    }
    @XmlElement
    public void setName(String name) {
       this.name = name;
    }
    
    public int getNbTrajet() {
       return nbtrajet;
    }
    @XmlElement
    public void setNbTrajet(int nbtrajet) {
       this.nbtrajet = nbtrajet;
    }
    
    public int getNbTrajetConduit() {
       return nbtrajetconduit;
    }
    @XmlElement
    public void setNbTrajetConduit(int nbtrajetconduit) {
       this.nbtrajetconduit = nbtrajetconduit;
    }
    
    public float getRatio() {
    	if(nbtrajet <= 0)
    		return (float) 0;
    	else
    		return (float) nbtrajetconduit/nbtrajet;
    }

	public String toString(){
		return this.name + "(" + this.id + ") ayant conduit "
				+ this.nbtrajetconduit + " fois parmi les "
				+ this.nbtrajet +" trajets auquel il a participe.";
	}
	
	public String toJSON(){
		
// JSON form -> {"object":[{"key1":"value1","key2":"value2"},{"key1":"value1","key2":"value2"}]}
		
		return "{\"id\":\"" + this.id + "\",\"name\":\"" + this.name 
				+ "\",\"total\":\"" + this.nbtrajet + "\",\"conduit\":\"" + this.nbtrajetconduit 
				+ "\"}";
	}
}
