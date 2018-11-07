package fr.ub.m2gl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

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
    
    @GET
    @Path("users")
    @Produces(MediaType.TEXT_PLAIN)
    public String userList() {
    	StringBuilder sb = new StringBuilder();
	    MongoClientURI connectionString = new MongoClientURI("mongodb://jacky:mdp1234@ds063870.mlab.com:63870/progweb");
	    MongoClient client = new MongoClient(connectionString);
	try {
	    MongoDatabase db = client.getDatabase("progweb");
	    MongoCollection<Document> collection = db.getCollection("users");
	    FindIterable<Document> iterable = collection.find();
	    for (Document doc : iterable) {
	    	sb.append(doc.get("firstName")).append(" : ");
	    	sb.append(doc.get("lastName")).append(System.getProperty("line.separator"));
	    }
	}
	catch (Exception e) {
		e.printStackTrace();
	} finally {
		client.close();
	}
	return sb.toString();
    }
    
    @GET
    @Path("{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("userName") String userName) {
      	StringBuilder sb = new StringBuilder();
	    MongoClientURI connectionString = new MongoClientURI("mongodb://jacky:mdp1234@ds063870.mlab.com:63870/progweb");
	    MongoClient client = new MongoClient(connectionString);
	try {
	    MongoDatabase db = client.getDatabase("progweb");
	    MongoCollection<Document> collection = db.getCollection("users");
	    FindIterable<Document> iterable = collection.find(eq("firstName", userName));
	    for (Document doc : iterable) {
	    	sb.append(doc.get("firstName")).append(" : ");
	    	sb.append(doc.get("lastName")).append(" ");
	    	sb.append(System.getProperty("line.separator"));
	    }
	}
	catch (Exception e) {
		e.printStackTrace();
	} finally {
		client.close();
	}
    return sb.toString();
    }
}
