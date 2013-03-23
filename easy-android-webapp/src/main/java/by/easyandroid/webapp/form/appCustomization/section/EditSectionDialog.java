package by.easyandroid.webapp.form.appCustomization.section;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.webapp.form.AbstractIdDialog;
import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.Field;

@ManagedBean
@SessionScoped
public class EditSectionDialog extends AbstractIdDialog {

	private String name;

	@Override
	public void open(ActionEvent event) {
		super.open(event);
		name = FacesUtil.getAttribute(event, Field.NAME);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}