package fr.ub.m2gl;

import static com.mongodb.client.model.Filters.eq;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class MongoRequests {
	private MongoClient _client;

	public MongoRequests () {
		MongoClientURI connectionString = new MongoClientURI("mongodb://jacky:mdp1234@ds063870.mlab.com:63870/progweb");
		_client = new MongoClient(connectionString);
	}

	public String registerUser (User user) {
		String out =  "";

		try {
			MongoDatabase db = _client.getDatabase("progweb");
			MongoCollection<Document> collection = db.getCollection("users");
			ObjectMapper mapper = new ObjectMapper ();
			String jsonString = mapper.writeValueAsString(user);
			Document doc = Document.parse(jsonString);
			collection.insertOne(doc);
			out = "INSERSION SUCESS\n";
		} catch (Exception e) {
			out = "INSERSION FAILED\n";
			e.printStackTrace();
		} finally {
			_client.close();
		}
		return out;
	}

	public ArrayList<User> userList () {
		ArrayList<User> listUser = new ArrayList<User>();
		try {
			MongoDatabase db = _client.getDatabase("progweb");
			MongoCollection<Document> collection = db.getCollection("users");
			FindIterable<Document> iterable = collection.find();
			ObjectMapper mapper = new ObjectMapper();
			for (Document doc : iterable) {
				User usr = mapper.readValue(doc.toJson(), User.class);
				listUser.add(usr);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			_client.close();
		}
		return listUser;
	}

	public String getUser (String userName) {
		String out = "";
		try {
			MongoDatabase db = _client.getDatabase("progweb");
			MongoCollection<Document> collection = db.getCollection("users");
			FindIterable<Document> iterable = collection.find(eq("firstName", userName));
			Document doc = iterable.first();
			out = doc.toJson();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			_client.close();
		}
		return out;
	}

	public String updateUser (String userName, User user) {
		String out = "";
		try {
			MongoDatabase db = _client.getDatabase("progweb");
			MongoCollection<Document> collection = db.getCollection("users");
			collection.updateOne(
					eq("firstName", userName), new Document("$set", new Document("firstName", 
					user.getFirstName()).append("lastName", user.getLastName())));
			out = "Update SUCCESS";
		} catch (Exception e) {
			out = "Update FAILED";
		} finally {
			_client.close();
		}
		return out;
	}
	
	public String deleteUser (String userName) {
		String out = "";
		try {
			MongoDatabase db = _client.getDatabase("progweb");
			MongoCollection<Document> collection = db.getCollection("users");
			collection.deleteOne(new Document("firstName", userName));
			out = "Delete SUCCESS";
		} catch (Exception e) {
			out = "Delete FAILED";
		} finally {
			_client.close();
		}
		return out;
	}
}
