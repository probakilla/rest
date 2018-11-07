package fr.ub.m2gl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
    	String out =  "";
		
	    MongoClientURI connectionString = new MongoClientURI("mongodb://jacky:mdp1234@ds063870.mlab.com:63870/progweb");
	    MongoClient client = new MongoClient(connectionString);
		try {
		    MongoDatabase db = client.getDatabase("progweb");
		    MongoCollection<Document> collection = db.getCollection("users");
		    ObjectMapper mapper = new ObjectMapper ();
		    String jsonString = mapper.writeValueAsString(user);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		    out = "INSERSION SUCESS";
		} catch (Exception e) {
		    out = "INSERSION FAILED";
		    e.printStackTrace();
		} finally {
		    client.close();
		}
    	return Response.status(200).entity(out).build();
    }   
}
