package by.easyandroid.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.database.service.TemplateService;
import by.easyandroid.database.service.UserService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.model.User;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.beans.UserBean;
import by.easyandroid.webapp.util.FacesUtil;

@ManagedBean
@RequestScoped
public class AppsCatalogForm extends AbstractBaseForm {

	@ManagedProperty(value = "#{templateService}")
	private TemplateService templateService;
	
	@ManagedProperty(value = "#{applicationInstanceService}")
	private ApplicationInstanceService applicationInstanceService;
	
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value = "#{copyTemplateDialog}")
	private CopyTemplateDialog copyTemplDialog;
	
	private List<ApplicationTemplate> templates = new ArrayList<ApplicationTemplate>();
	
	@Override
	public void init() throws DatabaseServiceException {
		templates = templateService.getAll();
	}
	
	public void copy() {
		try {
			// TODO put into service method and unit-test
			ApplicationTemplate template = templateService.get(copyTemplDialog.getTemplateId());
			if (template == null) {
				throw new DatabaseServiceException("Can not copy application model because template with id = '" + copyTemplDialog.getTemplateId() + " not exists");
			}
			
			ApplicationInstance copiedInstance = applicationInstanceService.fullCopy(template.getInitialInstance().getId());
			
			User currentUser = userBean.getUser();
			currentUser.getApplications().add(copiedInstance);
			userService.save(currentUser);
			
			copyTemplDialog.close();
			// TODO show confirmation dialog that copy was successful
		} catch (DatabaseServiceException e) {
			// TODO show message on web interface
			e.printStackTrace();
		}
		
		// TODO Show copy success dialog
	}
	
	public void showCopyDialog(ActionEvent event) {
		copyTemplDialog.setTemplateId(FacesUtil.getAttribute(event, "templateId"));
		copyTemplDialog.setTemplateTitle(FacesUtil.getAttribute(event, "templateTitle"));
		copyTemplDialog.open();
	}
	
	public List<ApplicationTemplate> getTemplates() throws DatabaseServiceException {
		return templates;
	}

	public TemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public CopyTemplateDialog getCopyTemplDialog() {
		return copyTemplDialog;
	}

	public void setCopyTemplDialog(CopyTemplateDialog copyTemplDialog) {
		this.copyTemplDialog = copyTemplDialog;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ApplicationInstanceService getApplicationInstanceService() {
		return applicationInstanceService;
	}

	public void setApplicationInstanceService(ApplicationInstanceService applicationInstanceService) {
		this.applicationInstanceService = applicationInstanceService;
	}
}