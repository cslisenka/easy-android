package by.easyandroid.webapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import by.easyandroid.database.service.UserService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;
import by.easyandroid.webapp.util.Navigation;

/**
 * Stores information about logged in user.
 * 
 * @author kslisenko
 */
@ManagedBean
@SessionScoped
public class UserBean implements PhaseListener {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private User user;
	
	private String login;
	private String password;
	private String passwordConfirm;
	private String email;	

	public String doRegister() {
		User user = new User();
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);

		// TODO check required fields
		
		try {
			userService.add(user);
			setUser(user);
			clearAll();
		} catch (DatabaseServiceException e) {
			// TODO show error
			e.printStackTrace();
			clearPassword();
		}

		return Navigation.MY_APPLICATIONS;
	}

	public String doLogin() {
		User user = userService.get(login, login);
		
		if (user != null) {
			setUser(user);
			clearAll();
			return Navigation.MY_APPLICATIONS;
		} else {
			clearPassword();
			return Navigation.LOGIN;	
		}
	}

	public String doLogout() {
		setUser(null);
		return Navigation.LOGIN;
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}
	
	public void clearAll() {
		clearPassword();
		login = null;
		email = null;
	}
	
	public void clearPassword() {
		password = null;
		passwordConfirm = null;
	}

	public void updateUserData() {
		user = userService.get(user.getId());
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void afterPhase(PhaseEvent event) {
		// Nothing to do
	}

	public void beforePhase(PhaseEvent event) {
		// TODO Check if user logged in
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}