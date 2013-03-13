package org.jazzteam.easyandroid.webapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jazzteam.easyandroid.webapp.form.RegisterForm;

import by.easyandroid.database.service.UserService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

/**
 * Stores information about logged in user.
 * 
 * @author kslisenko
 */
@ManagedBean
@RequestScoped
public class UserBean {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value = "#{registerForm}")
	private RegisterForm registerForm;

	private User user;
	private boolean isUserLoggedIn = false;

	public String doRegister() {
		User user = new User();
		user.setLogin(registerForm.getLogin());
		user.setEmail(registerForm.getEmail());
		user.setPassword(registerForm.getPassword());

		try {
			userService.add(user);
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RegisterForm getRegisterForm() {
		return registerForm;
	}

	public void setRegisterForm(RegisterForm registerForm) {
		this.registerForm = registerForm;
	}
}