package by.easyandroid.webapp.form.appCustomization;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.easyandroid.model.conference.Section;

@ManagedBean
@RequestScoped
public class SectionsForm extends AbstractConferenceBaseForm {

	private List<Section> sections = new ArrayList<Section>(); 
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO it's better to do that when enabling/disabling opening ConferenceForm
		// TODO check if selected conference belongs to user
		if (template != null) {
			sections = template.getModel().getSections();
		}
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}