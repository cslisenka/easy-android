package org.jazzteam.easyandroid.webapp.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Value bindings for register user
 * @author kslisenko
 */
@ManagedBean
@RequestScoped
public class RegisterForm extends LoginForm {

	private String passwordConfirm;
	private String email;

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