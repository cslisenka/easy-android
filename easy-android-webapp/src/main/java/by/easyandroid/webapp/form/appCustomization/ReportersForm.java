package by.easyandroid.webapp.form.appCustomization;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.easyandroid.model.conference.Reporter;

@ManagedBean
@RequestScoped
public class ReportersForm extends AbstractConferenceBaseForm {

	private List<Reporter> reporters = new ArrayList<Reporter>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO it's better to do that when enabling/disabling opening ConferenceForm
		// TODO check if selected conference belongs to user
		if (template != null) {
			reporters = template.getModel().getReporters();
		}
	}

	public List<Reporter> getReporters() {
		return reporters;
	}

	public void setReporters(List<Reporter> reporters) {
		this.reporters = reporters;
	}
}