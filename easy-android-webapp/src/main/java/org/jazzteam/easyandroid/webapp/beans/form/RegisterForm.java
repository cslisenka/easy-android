package org.jazzteam.easyandroid.webapp.beans.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Value bindings for register user popup
 * @author kslisenko
 */
@ManagedBean
@RequestScoped
public class RegisterForm {

	private String login;
	private String password;
	private String passwordConfirm;
	private String email;

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
}