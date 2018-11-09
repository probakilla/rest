package fr.ub.m2gl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class UserRessource {
	private MongoRequests _database;

	public UserRessource () {
		_database = new MongoRequests();
	}
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(User user) {
    	return Response.status(200).entity(_database.registerUser(user)).build();
    }
    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userList() {
    	return Response.status(200).entity(_database.userList()).build();
    }
    
    @GET
    @Path("/user/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userName") String userName) {
      	return Response.status(200).entity(_database.getUser(userName)).build();
    }
    
    @PUT
    @Path("/user/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(@PathParam("userName") String userName,
    		User user) {
      	return Response.status(200).entity(_database.updateUser(userName, user)).build();
    }
    
    @DELETE
    @Path("/user/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userName") String userName) {
    	return Response.status(200).entity(_database.deleteUser(userName)).build();
    }
}
