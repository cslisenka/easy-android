package by.easyandroid.webapp.form.appCustomization;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import by.easyandroid.model.conference.ConferenceInformation;

@ManagedBean
@RequestScoped
public class AboutConferenceForm extends AbstractConferenceBaseForm {
	
	private String name;
	
	private String website;
	
	private String about;
	
	@Override
	public void init() throws Exception {
		super.init();
		// TODO load values from database, get request parameter conference id
		// TODO check if selected conference belongs to user
		if (template != null) {
			ConferenceInformation information = template.getModel().getInformation();
			name = information.getTitle();
			website = information.getWebsiteUrl();
			about = information.getAbout();
		}
	}
	
	public void save(ActionEvent event) {
		if (template != null) {
			ConferenceInformation information = template.getModel().getInformation();
			information.setTitle(name);
			information.setWebsiteUrl(website);
			information.setAbout(about);
			instanceService.save(template);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public void deleteById(String id) {
		// TODO nothing to do, throw exception
	}
}