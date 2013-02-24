package by.easyandroid.model;

import by.easyandroid.model.conference.ConferenceApplicationModel;


/**
 * Android application template. User can create his own android applications from it.
 * 
 * @author kslisenko
 */
public class ApplicationTemplate extends Identity {

	/**
	 * Template title in applications catalog
	 */
	private String title;
	
	/**
	 * Template description in applications catalog
	 */
	private String description;
	
	/**
	 * Url of sample apk (based on this tamplate) for user downloading
	 */
	private String sampleApkUrl;
	
	/**
	 * Url or path to template sources (android project)
	 */
	private String templateSourceUrl;
	
	/**
	 * Data model for copying and creating templates
	 */
	private ConferenceApplicationModel templateModel;
	
	/**
	 * Url to web application part for editing application model
	 */
	private String webUIPartUrl;
	
	/**
	 * Converter for converting data model to additinal project sources
	 */
	private String converterClassName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSampleApkUrl() {
		return sampleApkUrl;
	}

	public void setSampleApkUrl(String sampleApkUrl) {
		this.sampleApkUrl = sampleApkUrl;
	}

	public String getTemplateSourceUrl() {
		return templateSourceUrl;
	}

	public void setTemplateSourceUrl(String templateSourceUrl) {
		this.templateSourceUrl = templateSourceUrl;
	}

	public ConferenceApplicationModel getTemplateModel() {
		return templateModel;
	}

	public void setTemplateModel(ConferenceApplicationModel templateModel) {
		this.templateModel = templateModel;
	}

	public String getWebUIPartUrl() {
		return webUIPartUrl;
	}

	public void setWebUIPartUrl(String webUIPartUrl) {
		this.webUIPartUrl = webUIPartUrl;
	}

	public String getConverterClassName() {
		return converterClassName;
	}

	public void setConverterClassName(String converterClassName) {
		this.converterClassName = converterClassName;
	}
}