package by.easyandroid.model.conference;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.model.Identity;

/**
 * Model with Conference application data. Represents one application entity in system.
 * May be copied and become another application instance.
 * 
 * @author kslisenko
 */
public class ConferenceApplicationModel extends Identity {

	private List<Report> reports = new ArrayList<Report>();
	
	private List<Reporter> reporters = new ArrayList<Reporter>();
	
	private List<Section> sections = new ArrayList<Section>();
	
	private List<Category> categories = new ArrayList<Category>();
	
	/**
	 * Color settings, used images, UI settings
	 */
	private ConferenceDesignSettings designSettings = new ConferenceDesignSettings();
	
	/**
	 * Conference data: title, description, dates, etc.
	 */
	private ConferenceInformation information = new ConferenceInformation();

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<Reporter> getReporters() {
		return reporters;
	}

	public void setReporters(List<Reporter> reporters) {
		this.reporters = reporters;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public ConferenceDesignSettings getDesignSettings() {
		return designSettings;
	}

	public void setDesignSettings(ConferenceDesignSettings designSettings) {
		this.designSettings = designSettings;
	}

	public ConferenceInformation getInformation() {
		return information;
	}

	public void setInformation(ConferenceInformation information) {
		this.information = information;
	}
}