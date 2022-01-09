package com.aliens.demorest;


 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

@Path("aliens")
public class AlienResource {

	AlienRepository repo= new AlienRepository();
	
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Alien> getAliens() {
	   return repo.getAliens();
	}
 

   @GET
   @Path("alien/{id}")
   @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
 public Alien getAlien(@PathParam("id") int id ) 
    {
	  return repo.getAlien(id);
 }
	 

	
     @POST //Annotation used to create a resource in DB
	 @Path("alien") //We are changing the path for the post operation b using the path variable
     @Consumes(MediaType.APPLICATION_XML)
	 public Alien createalien(Alien al) {
	 System.out.println(al); //to check if we are recieving the object or not 
	 repo.createAlien(al);
	 return al;
     }
	 
	 
	 @PUT //Annotation used to create a resource in DB
	 @Path("alien") //We are changing the path for the post operation b using the path variable
	 @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 
	 //TO_DO--Before updating check if object exists in the DB or not or else create a new object 
	 public Alien updateAlien(Alien al) {
	 System.out.println(al); //to check if we are receiving the object or not
	 if(repo.getAlien(al.getId()).getId()==0) {
		 System.out.println("resource doesnot exist to update so craeting a new resource ");
		 repo.createAlien(al);
	 }
	 else {
	 repo.updateAlien(al);
	 }
	 return al;
	 
	 }
	 
	 @DELETE
	 @Path("alien/delete/{id}")
	 @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	 public String killAlien(@PathParam("id") int id ) 
	    {
		 
		 if(repo.getAlien(id).getId()!=0) {
			return repo.killAlien(id) ;
		 }
		 
		 else {
			 return "ID not found";
		 }
	 }
		

}
