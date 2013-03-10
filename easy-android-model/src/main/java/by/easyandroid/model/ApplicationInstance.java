package by.easyandroid.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

import by.easyandroid.model.conference.ConferenceApplicationModel;


/**
 * Android application, created by user.
 * 
 * @author kslisenko
 */
public class ApplicationInstance extends Identity {

	/**
	 * Template, which this application was copied
	 */
	@DBRef
	private ApplicationTemplate template;
	
	/**
	 * Application name
	 */
	private String name;
	
	/**
	 * Model with application data
	 */
	private ConferenceApplicationModel model;
	
	private Date creationDate;

	/**
	 * Url of last builded apk to download
	 */
	private String lastCreatedApkUrl;
	
	/**
	 * True if there were no changes in the model until last apk build
	 */
	private boolean lastApkUpToDate;

	public ApplicationTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ApplicationTemplate template) {
		this.template = template;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConferenceApplicationModel getModel() {
		return model;
	}

	public void setModel(ConferenceApplicationModel model) {
		this.model = model;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastCreatedApkUrl() {
		return lastCreatedApkUrl;
	}

	public void setLastCreatedApkUrl(String lastCreatedApkUrl) {
		this.lastCreatedApkUrl = lastCreatedApkUrl;
	}

	public boolean isLastApkUpToDate() {
		return lastApkUpToDate;
	}

	public void setLastApkUpToDate(boolean lastApkUpToDate) {
		this.lastApkUpToDate = lastApkUpToDate;
	}
}