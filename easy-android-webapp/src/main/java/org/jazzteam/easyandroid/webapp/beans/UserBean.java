package org.jazzteam.easyandroid.webapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jazzteam.easyandroid.webapp.beans.form.RegisterForm;
import org.jazzteam.easyandroid.webapp.util.FacesUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.UserService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
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

	public String doRegister() {
		RegisterForm form = FacesUtil.getRequestBean("registerForm"); 
		ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		UserService service = new UserService(mongoOperation);
		
		User user = new User();
		user.setLogin(form.getLogin());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());

		try {
			service.add(user);
		} catch (DatabaseServiceException e) {
			// TODO add error jsf message
			e.printStackTrace();
		}
		
		setUser(user);
		
		
		return "/myApplications.xhtml?faces-redirect=true";
	}
	
	public String doLogin() {
		
		
		return "/myApplications.xhtml?faces-redirect=true";
	}
	
	public void doLogout() {
		
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