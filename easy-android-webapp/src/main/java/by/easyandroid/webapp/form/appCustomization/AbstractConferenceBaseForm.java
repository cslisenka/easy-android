package by.easyandroid.webapp.form.appCustomization;

import javax.faces.bean.ManagedProperty;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.webapp.AbstractBaseForm;
import by.easyandroid.webapp.util.Bean;

public abstract class AbstractConferenceBaseForm extends AbstractBaseForm {

	@ManagedProperty(value = Bean.SRV_APPLICATION)
	protected ApplicationInstanceService instanceService;
	
	@ManagedProperty(value = Bean.FRM_CONFERENCE)
	protected ConferenceForm conferenceForm;	
	
	protected ApplicationInstance template;
	
	@Override
	public void init() throws Exception {
		String selectedTemplateId = conferenceForm.getSelectedTemplateId();
		if (selectedTemplateId != null) {
			template = instanceService.get(selectedTemplateId);
		}
	}
	
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