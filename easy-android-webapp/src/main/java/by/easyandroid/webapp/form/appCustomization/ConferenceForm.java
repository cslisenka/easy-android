package by.easyandroid.webapp.form.appCustomization;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.webapp.util.FacesUtil;
import by.easyandroid.webapp.util.Field;

@ManagedBean
@SessionScoped
public class ConferenceForm {

	private String selectedTemplateId;

	public void open(ActionEvent event) {
		selectedTemplateId = FacesUtil.getAttribute(event, Field.TEMPLATE_ID);
	}
	
	public String getSelectedTemplateId() {
		return selectedTemplateId;
	}

	public void setSelectedTemplateId(String selectedTemplateId) {
		this.selectedTemplateId = selectedTemplateId;
	}
}