package org.jazzteam.easyandroid.webapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jazzteam.easyandroid.webapp.form.LoginForm;
import org.jazzteam.easyandroid.webapp.form.RegisterForm;
import org.jazzteam.easyandroid.webapp.util.FacesUtil;
import org.jazzteam.easyandroid.webapp.util.Navigation;

import by.easyandroid.database.service.UserService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

/**
 * Stores information about logged in user.
 * 
 * @author kslisenko
 */
@ManagedBean
@SessionScoped
public class UserBean {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private User user;

	public String doRegister() throws DatabaseServiceException {
		RegisterForm registerForm = FacesUtil.getRequestBean("registerForm");
		User user = new User();
		user.setLogin(registerForm.getLogin());
		user.setEmail(registerForm.getEmail());
		user.setPassword(registerForm.getPassword());

		// TODO check required fields
		
		userService.add(user);

		setUser(user);

		return Navigation.MY_APPLICATIONS;
	}

	public String doLogin() {
		LoginForm loginForm = FacesUtil.getRequestBean("loginForm");
		User user = userService.get(loginForm.getLogin(), loginForm.getPassword());
		// TODO check wrong username
		setUser(user);
		return Navigation.MY_APPLICATIONS;
	}

	public String doLogout() {
		setUser(null);
		return Navigation.LOGIN_PAGE;
	}

	public boolean isUserLoggedIn() {
		return user != null;
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
}