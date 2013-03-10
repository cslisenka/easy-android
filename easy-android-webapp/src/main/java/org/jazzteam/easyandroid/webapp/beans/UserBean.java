package org.jazzteam.easyandroid.webapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import by.easyandroid.model.User;

/**
 * Stores information about logged in user.
 * @author kslisenko
 */
@ManagedBean
@RequestScoped
public class UserBean {

	private User user;
	private boolean isUserLoggedIn = false;

	private String login;
	
	// TODO use private foeld and managed property @ManagedProperty
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String registerNewUser() {
		RegisterUserPopup popup = (RegisterUserPopup) FacesContext.getCurrentInstance()
			    .getExternalContext().getSessionMap().get("registerUserPopup");
		
		String login = popup.getLogin();
		String password = popup.getPassword();
		
		popup.setLogin("test");
		
		return "/myApplications.xhtml?faces-redirect=true";
		
		// Do registration
	}
	
	public String doLogin() {
		
		
		return "/myApplications.xhtml?faces-redirect=true";
	}
	
	public void logout() {
		
	}
	
	public boolean isUserLoggedIn() {
		return isUserLoggedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}