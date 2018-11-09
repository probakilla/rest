package fr.ub.m2gl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String _firstName;
	private String _lastName;
	
	public User () {
		_firstName = "N/A";
		_lastName = "N/A";
	}
	
	public User (String firstName, String lastName) {
		_firstName = firstName;
		_lastName = lastName;
	}
	
	public String getFirstName () {
		return _firstName;
	}
	
	public String getLastName () {
		return _lastName;
	}
	
	public void setFirstName (String firstName) {
		_firstName = firstName;
	}
	
	public void setLastName (String lastName) {
		_lastName = lastName;
	}
	
	public String toString () {
		StringBuffer sb = new StringBuffer (_firstName);
		sb.append(" : ").append(_lastName);
		return sb.toString();
	}
}
