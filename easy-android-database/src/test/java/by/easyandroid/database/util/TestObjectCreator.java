package by.easyandroid.database.util;

import java.util.GregorianCalendar;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.ConferenceDesignSettings;
import by.easyandroid.model.conference.ConferenceInformation;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;

public class TestObjectCreator {

	public static Report fakeReport(String title) {
		Report newReport = new Report();
		newReport.setTitle(title);
		newReport.setTime(new GregorianCalendar().getTime());
		newReport.setDescription(title + " description");
		return newReport;
	}
	
	public static Category fakeCategory(String name) {
		Category category = new Category();
		category.setName(name);
		return category;		
	}
	
	public static Section fakeSection(String name) {
		Section section = new Section();
		section.setMapImageUrl("url");
		section.setName(name);
		return section;
	}
	
	public static Reporter fakeReporter(String name) {
		Reporter reporter = new Reporter();
		reporter.setName(name);
		reporter.setPosition(name + " position");
		reporter.setEmail(name + "@gmail.com");
		reporter.setDescription(name + " description");
		reporter.setCompany(name + " company");
		return reporter;
	}
	
	public static ConferenceApplicationModel fakeModel(String conferenceTitle) {
		ConferenceApplicationModel model = new ConferenceApplicationModel();
		
		ConferenceDesignSettings designSettings = new ConferenceDesignSettings();
		designSettings.setBackgroundColor("testcolor");
		designSettings.setConferenceLogoUrl("logourl");
		designSettings.setTextColor("textcolor");
		model.setDesignSettings(designSettings);
		
		ConferenceInformation information = new ConferenceInformation();
		information.setTitle(conferenceTitle);
		information.setAbout(conferenceTitle + " about");
		information.setWebsiteUrl("http://" + conferenceTitle.replace(" ", "").trim().toLowerCase() + ".com");
		model.setInformation(information);
		
		return model;
	}
	
	public static ApplicationInstance fakeInstance(String name) {
		ApplicationInstance instance = new ApplicationInstance();
		instance.setName(name);
		instance.setLastApkUpToDate(true);
		instance.setLastCreatedApkUrl("http://lastcreated.com");
		instance.setModel(fakeModel(name + " conference title"));
		return instance;
	}
	
	public static ApplicationTemplate fakeTemplate(String title) {
		ApplicationTemplate template = new ApplicationTemplate();
		template.setConverterClassName("convertername");
		template.setDescription("description");
		template.setSampleApkUrl("http://sampleapk.com");
		template.setTemplateSourceUrl("template source url");
		template.setTitle(title);
		template.setWebUIPartUrl("web ui part");
		
		return template;
	}
}