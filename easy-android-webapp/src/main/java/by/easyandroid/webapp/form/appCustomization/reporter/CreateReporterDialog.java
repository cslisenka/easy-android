package by.easyandroid.webapp.form.appCustomization.reporter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.webapp.AbstractBaseDialog;

@ManagedBean
@SessionScoped
public class CreateReporterDialog extends AbstractBaseDialog {

	private String name;
	
	private String description;
	
	private String email;
	
	private String company;
	
	private String position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}