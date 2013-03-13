package by.easyandroid.webapp.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Value bindings for login
 * @author kslisenko
 */
@ManagedBean
@RequestScoped
public class LoginForm {

	private String login;
	private String password;

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
}