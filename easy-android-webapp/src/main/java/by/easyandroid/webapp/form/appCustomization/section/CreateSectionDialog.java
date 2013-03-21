package by.easyandroid.webapp.form.appCustomization.section;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.webapp.AbstractBaseDialog;

@ManagedBean
@SessionScoped
public class CreateSectionDialog extends AbstractBaseDialog {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}