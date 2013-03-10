package by.easyandroid.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User extends Identity {

	private String login;
	
	private String password;
	
	private String email;
	
	@DBRef
	private List<ApplicationInstance> applications = new ArrayList<ApplicationInstance>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ApplicationInstance> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationInstance> applications) {
		this.applications = applications;
	}
}