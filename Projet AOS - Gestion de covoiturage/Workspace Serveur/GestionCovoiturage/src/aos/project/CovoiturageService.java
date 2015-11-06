package aos.project;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Dorian Davi
 *
 */

@Path("/CovoiturageService")
public class CovoiturageService {
	
	CovoiturageDao cDAO = new CovoiturageDao();

	@GET
	@Path("/conducteurs")
	@Produces(MediaType.APPLICATION_XML)
	public String getConducteurs(){
		return cDAO.getAllConducteursToJSONString();
	}
	
	@GET
	@Path("/conducteurs/{idC}")
	@Produces(MediaType.APPLICATION_XML)
	public Conducteur getConducteur(@PathParam("idC") int idC){
		return cDAO.getConducteur(idC);
	}
	
	@GET
	@Path("/conducteurs/ratio/{idC}")
	@Produces(MediaType.APPLICATION_XML)
	public float getRatio(@PathParam("idC") int idC){
		return cDAO.getRatio(idC);
	}
	
	@GET
	@Path("/conducteurs/ratio")
	@Produces(MediaType.APPLICATION_XML)
	public String getRatio(){
		return ""+cDAO.getRatioMin();
	}
	
	@PUT
	   @Path("/conducteurs/add")
	   @Produces(MediaType.APPLICATION_XML)
	   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	   public String createConducteur(
	      @FormParam("name") String name,
	      @Context HttpServletResponse servletResponse) throws IOException{
	      boolean result = cDAO.addConducteur(name);
	      if(result == true){
	         return "Le conducteur a bien ete ajoute";
	      }
	      return "Le conducteur n'a pas pu etre ajoute";
	   }
	
	@DELETE
	@Path("/conducteurs/{idC}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteConducteur(@PathParam("idC") int idC){
		 boolean result = cDAO.deleteConducteur(idC);
		if(result == true){
			return "Suppression reussie";
		} else{
			return "Suppression echouee";
		} 
	}
	
	@POST
	@Path("/conducteurs/increase/total")
	@Produces(MediaType.APPLICATION_XML)
	public String increaseTotal(@FormParam("idC") int idC,
			@Context HttpServletResponse servletResponse) throws IOException{
		return Boolean.toString(cDAO.incrTotal(idC));
	}
		
	@POST
	@Path("/conducteurs/decrease/total")
	@Produces(MediaType.APPLICATION_XML)
	public String decreaseTotal(@FormParam("idC") int idC,
			@Context HttpServletResponse servletResponse) throws IOException{
		return Boolean.toString(cDAO.decrTotal(idC));
	}
	
	@POST
	@Path("/conducteurs/increase/conduit")
	@Produces(MediaType.APPLICATION_XML)
	public String increaseConduit(@FormParam("idC") int idC,
			@Context HttpServletResponse servletResponse) throws IOException{
		return Boolean.toString(cDAO.incrConduit(idC));
	}
		
	@POST
	@Path("/conducteurs/decrease/conduit")
	@Produces(MediaType.APPLICATION_XML)
	public String decreaseConduit(@FormParam("idC") int idC,
			@Context HttpServletResponse servletResponse) throws IOException{
		return Boolean.toString(cDAO.decrConduit(idC));
	}
}
