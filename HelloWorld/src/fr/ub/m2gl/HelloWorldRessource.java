package fr.ub.m2gl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloWorld")
public class HelloWorldRessource {
	
    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String getHelloWorld() {
        return "Hello World from text/plain";
    }
}
