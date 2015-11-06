package aos.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Dorian Davi
 *
 */

public class CovoiturageDao {
	/**
	 * 
	 * @param conducteurs
	 */
	public void saveConducteurList(List<Conducteur> conducteurs){
		String location = "Conducteurs.dat";
		
		try {
			File file = new File(location);
			FileOutputStream fos;
			
			fos = new FileOutputStream(file);
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(conducteurs);
			oos.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Conducteur> getAllConducteurs(){
		List<Conducteur> conducteurs = null;
		String location = "Conducteurs.dat";
		
		try {
	         File file = new File(location);
	         if (!file.exists()) {
	        	 Conducteur user = new Conducteur("Dorian");
	        	 conducteurs = new ArrayList<Conducteur>();
	        	 conducteurs.add(user);
	        	 saveConducteurList(conducteurs);		
	         }
	         else{
	            FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            conducteurs = (List<Conducteur>) ois.readObject();
	            ois.close();
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conducteurs;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAllConducteursToJSONString(){
		List<Conducteur> conducteurList = getAllConducteurs();
		
		String conducteursString = "{\"conducteurs\":[";
		
		for(Conducteur c: conducteurList){
			conducteursString += c.toJSON();
		}
		
		conducteursString += "]}";
		
		return conducteursString;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Conducteur getConducteur(int id){
	    List<Conducteur> conducteursList = getAllConducteurs();

	    for(Conducteur c: conducteursList){
	       if(c.getId() == id){
	          return c;
	       }
	    }
	    return null;
	 }
	 
	/**
	 * Retourne le ratio relatif a un conducteur (nb de trajets conduits / nb de trajets totaux)
	 * @param id
	 * @return Ratio du conducteur dont l'identifiant est id
	 */
	 public float getRatio(int id){
		 List<Conducteur> conducteursList = getAllConducteurs();

		 for(Conducteur c: conducteursList){
			 if(c.getId() == id){
				 return c.getRatio();
			 }
		 }
		 return 0;
	 }
	 
	/**
	 * Retourne le ratio minimum
	 * @param id
	 * @return Ratio du conducteur ayant le moins conduit
	 */
	 public String getRatioMin(){
		 List<Conducteur> conducteursList = getAllConducteurs();
		 float ratiomin = 1;
		 Conducteur cMin = null;
		 
		 for(Conducteur c: conducteursList){
			 if(ratiomin > c.getRatio()){
				 ratiomin = c.getRatio();
				 cMin = c;
			 }
		 }
		 if(cMin == null){
			 return "Il y a eu un probleme ! Oups...";
		 }else{
			 return ""+ratiomin+" correspondant au conducteur "+getConducteur(cMin.getId());//cMin.getName()+" ("+cMin.getId()+")"; 
		 }
	 }
	
	 /**
	  * Maintient a jour la valeur unique id
	  * @return id courant dans nos donnees
	  */
	public int getCurrentId(){
		int currentId = 0;
		List<Conducteur> conducteurs = getAllConducteurs();
		if(conducteurs != null)
			for(Conducteur c : conducteurs){
				if(c.getId() > currentId)
					currentId = c.getId();
			}
		return currentId+1;
	}
	
	/**
	 * Ajout d'un conducteur 
	 * @param name
	 * @return
	 */
	public boolean addConducteur(String name){

		Conducteur conducteur = new Conducteur(name, getCurrentId());
		List<Conducteur> conducteursList = getAllConducteurs();
		boolean conducteurExists = false;
		for (Conducteur c : conducteursList){
			if(conducteur.getId() == c.getId()){
				conducteurExists = true;
				break;
			}
		}
		if(!conducteurExists){
			conducteursList.add(conducteur);
			saveConducteurList(conducteursList);
			return true;
		}
		return false;
	}
	
	/**
	 * Suppression d'un conducteur
	 * @param id
	 * @return
	 */
	public boolean deleteConducteur(int id){
		List<Conducteur> conducteurList = getAllConducteurs();

		for(Conducteur c: conducteurList){
			if(c.getId() == id){
				int index = conducteurList.indexOf(c);			
	            conducteurList.remove(index);
	            saveConducteurList(conducteurList);
	            return true;   
			}
		}		
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean incrTotal(int id){
		List<Conducteur> conducteursList = getAllConducteurs();
		for(Conducteur c: conducteursList){
			if(c.getId() == id){
				c.setNbTrajet(c.getNbTrajet()+1);
	            saveConducteurList(conducteursList);
	            return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean decrTotal(int id){
		List<Conducteur> conducteursList = getAllConducteurs();
		for(Conducteur c: conducteursList){
			if(c.getId() == id){
				if(c.getNbTrajet()>0){
					c.setNbTrajet(c.getNbTrajet()-1);
		            saveConducteurList(conducteursList);
		            return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean incrConduit(int id){
		List<Conducteur> conducteursList = getAllConducteurs();
		for(Conducteur c: conducteursList){
			if(c.getId() == id){
				c.setNbTrajetConduit(c.getNbTrajetConduit()+1);
				c.setNbTrajet(c.getNbTrajet()+1);
	            saveConducteurList(conducteursList);
	            return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean decrConduit(int id){
			List<Conducteur> conducteursList = getAllConducteurs();
			for(Conducteur c: conducteursList){
				if(c.getId() == id){
					if(c.getNbTrajetConduit()>0){
						c.setNbTrajetConduit(c.getNbTrajetConduit()-1);
			            saveConducteurList(conducteursList);
			            return true;
					}else{
						return false;
					}
				}
			}
			return false;
	}
}
