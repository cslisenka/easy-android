package by.easyandroid.webapp.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.easyandroid.model.ApplicationInstance;

@ManagedBean
@SessionScoped
public class ApplicationDialog extends AbstractEntityDialog<ApplicationInstance> {

	public ApplicationDialog() throws InstantiationException, IllegalAccessException {
		super(ApplicationInstance.class);
	}
}