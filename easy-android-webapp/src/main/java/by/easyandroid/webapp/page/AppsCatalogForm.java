package by.easyandroid.webapp.page;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import by.easyandroid.database.service.TemplateService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.webapp.AbstractBaseForm;

@ManagedBean
@RequestScoped
public class AppsCatalogForm extends AbstractBaseForm {

	@ManagedProperty(value = "#{templateService}")
	private TemplateService templateService;
	
	private List<ApplicationTemplate> templates = new ArrayList<ApplicationTemplate>();

	@Override
	public void init() throws DatabaseServiceException {
		templates = templateService.getAll();
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
}