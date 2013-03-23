package by.easyandroid.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.User;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.beans.UserBean;
import by.easyandroid.webapp.util.Bean;

@ManagedBean
@RequestScoped
public class MyApplicationsForm extends AbstractBaseForm {

	@ManagedProperty(value = Bean.SRV_APPLICATION)
	private ApplicationInstanceService instanceService;
	
	@ManagedProperty(value = Bean.BN_USER)
	private UserBean userBean;
	
	private List<ApplicationInstance> templates = new ArrayList<ApplicationInstance>();
	
	@Override
	public void init() {
		User currentUser = userBean.getUser();
		if (currentUser != null) {
			templates = currentUser.getApplications();
		}
	}

	public List<ApplicationInstance> getTemplates() {
		return templates;
	}

	public ApplicationInstanceService getInstanceService() {
		return instanceService;
	}

	public void setInstanceService(ApplicationInstanceService instanceService) {
		this.instanceService = instanceService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}