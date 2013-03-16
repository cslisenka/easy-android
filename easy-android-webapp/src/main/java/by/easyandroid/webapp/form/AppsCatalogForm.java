package by.easyandroid.webapp.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.TemplateService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.util.FacesUtil;

@ManagedBean
@RequestScoped
public class AppsCatalogForm extends AbstractBaseForm {

	@ManagedProperty(value = "#{templateService}")
	private TemplateService templateService;

	@ManagedProperty(value = "#{copyTemplateDialog}")
	private CopyTemplateDialog copyTemplDialog;
	
	private List<ApplicationTemplate> templates = new ArrayList<ApplicationTemplate>();
	
	@Override
	public void init() throws DatabaseServiceException {
		templates = templateService.getAll();
	}
	
	public void copy() {
		// TODO do copy
		
		copyTemplDialog.close();
		
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

	public void setTemplates(List<ApplicationTemplate> templates) {
		this.templates = templates;
	}
}