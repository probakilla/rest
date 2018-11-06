package fr.ub.m2gl;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloWorldRessource {
	
    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String getHelloWorld() {
        return "Hello World from text/plain";
    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/plain")
    public String registerUser(@FormParam(value="firstName") String firstName,
    						 @FormParam(value="lastName") String lastName) {
    	return firstName + " : " + lastName;
    }
}
