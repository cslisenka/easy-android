package by.easyandroid.webapp.form.appCustomization;

import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.RequestAttr;

public abstract class AbstractConferenceBaseForm extends AbstractBaseForm {

	@ManagedProperty(value = "#{applicationInstanceService}")
	protected ApplicationInstanceService instanceService;
	
	@ManagedProperty(value = "#{conferenceForm}")
	protected ConferenceForm conferenceForm;	
	
	protected ApplicationInstance template;
	
	@Override
	public void init() throws Exception {
		String selectedTemplateId = conferenceForm.getSelectedTemplateId();
		if (selectedTemplateId != null) {
			template = instanceService.get(selectedTemplateId);
		}
	}

	public void delete(ActionEvent event) {
		deleteById(FacesUtil.getAttribute(event, RequestAttr.OBJID));
	}
	
	public abstract void deleteById(String id);
	
	public ApplicationInstanceService getInstanceService() {
		return instanceService;
	}

	public void setInstanceService(ApplicationInstanceService instanceService) {
		this.instanceService = instanceService;
	}

	public ConferenceForm getConferenceForm() {
		return conferenceForm;
	}

	public void setConferenceForm(ConferenceForm conferenceForm) {
		this.conferenceForm = conferenceForm;
	}
}