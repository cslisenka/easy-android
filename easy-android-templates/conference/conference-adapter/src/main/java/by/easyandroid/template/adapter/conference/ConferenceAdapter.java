package by.easyandroid.template.adapter.conference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.ITemplateAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;
import by.easyandroid.template.adapter.util.AdapterUtil;
import by.easyandroid.template.adapter.util.EntityConverter;
import by.easyandroid.template.adapter.util.XmlUtil;

/**
 * Adapter for conference application
 * @author kslisenko
 */
public class ConferenceAdapter implements ITemplateAdapter {

	private static final String ASSETS_CONFERENCE_DATA_XML = "assets/conference_data.xml";

	private static final String RES_VALUES_STRINGS_XML = "res/values/strings.xml";

	private static final String ASSETS_ABOUT_HTML = "assets/about.html";
	
	private String sourcesPath;
	private ConferenceApplicationModel model;
	
	public ConferenceAdapter(ConferenceApplicationModel model) {
		this.model = model;
	}

	public List<FileEntry> convert() throws TemplateAdapterException {
		List<FileEntry> result = new ArrayList<FileEntry>();

		result.add(createAdoutConferenceWebPage());
		result.add(createSettingsXml());
		result.add(createConferenceDataXml());
		
		return result;
	}
	
	public void setSourcesPath(String sourcesPath) {
		this.sourcesPath = sourcesPath;
	}
	
	private FileEntry createConferenceDataXml() throws TemplateAdapterException {
		try {
			Document doc = XmlUtil.createDocument();
			Element conference = doc.createElement("conference");
			doc.appendChild(conference);
			
			// Add reports
			Element reports = XmlUtil.createChildElement(conference, "reports", doc); 
			for (Report oneReport : model.getReports()) {
				reports.appendChild(EntityConverter.toXml(oneReport, doc));
			}
			
			// Add reporters
			Element reporters = XmlUtil.createChildElement(conference, "reporters", doc);  
			for (Reporter oneReporter : model.getReporters()) {
				reporters.appendChild(EntityConverter.toXml(oneReporter, doc));
			}
			
			// Add sections
			Element sections = XmlUtil.createChildElement(conference, "sections", doc); 
			for (Section section : model.getSections()) {
				sections.appendChild(EntityConverter.toXml(section, doc));
			}
			
			// Add categories
			Element categories = XmlUtil.createChildElement(conference, "categories", doc); 
			for (Category category : model.getCategories()) {
				categories.appendChild(EntityConverter.toXml(category, doc));
			}			
			
			return AdapterUtil.create(ASSETS_CONFERENCE_DATA_XML, doc);
		} catch (ParserConfigurationException e) {
			throw new TemplateAdapterException("Error when creating 'conference_data.xml'", e);
		}
	}

	private FileEntry createSettingsXml() throws TemplateAdapterException {
		Document doc = AdapterUtil.getProjectXml(sourcesPath, RES_VALUES_STRINGS_XML);

		Map<String, String> stringResources = new HashMap<String, String>();
		stringResources.put("main_conf_name", model.getInformation().getTitle());
		stringResources.put("main_website_url", model.getInformation().getWebsiteUrl());
		
		AdapterUtil.updateStringsXml(doc, stringResources);
		
		return AdapterUtil.create(RES_VALUES_STRINGS_XML, doc);
	}

	private FileEntry createAdoutConferenceWebPage() throws TemplateAdapterException {
		org.jsoup.nodes.Document webpage = AdapterUtil.getProjectWebPage(sourcesPath, ASSETS_ABOUT_HTML); 
		String allText = webpage.html();
		String body = webpage.body().text();
		return new FileEntry(ASSETS_ABOUT_HTML, allText.replace(body, model.getInformation().getAbout()));
	}
}