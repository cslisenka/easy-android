package by.easyandroid.template.adapter.conference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.ITemplateAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;
import by.easyandroid.template.adapter.util.XmlUtil;

/**
 * Adapter for conference application
 * @author kslisenko
 */
public class ConferenceAdapter implements ITemplateAdapter {

	private String sourcesPath;
	private ConferenceApplicationModel model;
	
	public ConferenceAdapter(ConferenceApplicationModel model) {
		this.model = model;
	}

	public List<FileEntry> convert() throws TemplateAdapterException {
		List<FileEntry> result = new ArrayList<FileEntry>();

		// TODO create util for adapter for creating different resources from workspace
		// TODO put in util: modifyExistingResource()
		result.add(createAdoutConferenceWebPage());
		result.add(createSettingsXml());
		result.add(createConferenceDataXml());
		
		return result;
	}

	private FileEntry createConferenceDataXml() throws TemplateAdapterException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document doc = builder.newDocument();
			Element conference = doc.createElement("conference");
			doc.appendChild(conference);
			
			// Add reports
			Element reports = doc.createElement("reports");
			conference.appendChild(reports);
			for (Report oneReport : model.getReports()) {
				Element reportElement = doc.createElement("report");
				reports.appendChild(reportElement);
				
				reportElement.setAttribute("id", oneReport.getId());
				reportElement.setAttribute("category", oneReport.getCategory().getId());
				reportElement.setAttribute("reporter", oneReport.getReporter().getId());
				reportElement.setAttribute("section", oneReport.getSection().getId());
				
				reportElement.appendChild(XmlUtil.appendTextNode("title", oneReport.getTitle(), doc));
				reportElement.appendChild(XmlUtil.appendTextNode("description", oneReport.getDesctiption(), doc));
				
				Element reportDate = doc.createElement("date");
				reportDate.setAttribute("year", oneReport.getTime().getYear() + "");
				reportDate.setAttribute("month", oneReport.getTime().getMonth() + "");
				reportDate.setAttribute("day", oneReport.getTime().getDate() + "");
				reportDate.setAttribute("hours", oneReport.getTime().getHours() + "");
				reportDate.setAttribute("minutes", oneReport.getTime().getMinutes() + "");
				reportElement.appendChild(reportDate);
			}
			
			// Add reporters
			Element reporters = doc.createElement("reporters");
			conference.appendChild(reporters);
			
			for (Reporter oneReporter : model.getReporters()) {
				Element reporterElement = doc.createElement("reporter");
				reporters.appendChild(reporterElement);
				
				reporterElement.setAttribute("id", oneReporter.getId());
				reporterElement.appendChild(XmlUtil.appendTextNode("name", oneReporter.getName(), doc));
				reporterElement.appendChild(XmlUtil.appendTextNode("description", oneReporter.getDescription(), doc));
				reporterElement.appendChild(XmlUtil.appendTextNode("email", oneReporter.getEmail(), doc));
				reporterElement.appendChild(XmlUtil.appendTextNode("company", oneReporter.getCompany(), doc));	
				reporterElement.appendChild(XmlUtil.appendTextNode("position", oneReporter.getPosition(), doc));							
			}
			
			// Add sections
			Element sections = doc.createElement("sections");
			conference.appendChild(sections);
			
			for (Section section : model.getSections()) {
				Element sectionElement = doc.createElement("section");
				sections.appendChild(sectionElement);
				
				sectionElement.setAttribute("id", section.getId());
				sectionElement.appendChild(XmlUtil.appendTextNode("name", section.getName(), doc));					
			}
			
			// Add categories
			Element categories = doc.createElement("categories");
			conference.appendChild(categories);
			
			for (Category category : model.getCategories()) {
				Element categoryElement = doc.createElement("category");
				categories.appendChild(categoryElement);
				
				categoryElement.setAttribute("id", category.getId());
				categoryElement.appendChild(XmlUtil.appendTextNode("name", category.getName(), doc));					
			}			
			
			return new FileEntry("assets/conference_data.xml", XmlUtil.getXml(doc));
		} catch (ParserConfigurationException e) {
			throw new TemplateAdapterException("Error when creating conference_data.xml", e);
		} catch (TransformerException e) {
			throw new TemplateAdapterException("Error when creating conference_data.xml", e);
		}
	}

	private FileEntry createSettingsXml() throws TemplateAdapterException {
		File xmlFile = new File(sourcesPath + File.separator + "res" + File.separator + "values" + File.separator + "strings.xml");
		if (!xmlFile.exists()) {
			throw new TemplateAdapterException("Xml file 'settings.xml' is not exists in project sources");
		}
		
		try {
			org.w3c.dom.Document doc = XmlUtil.parse(xmlFile);

			Node resources = XmlUtil.getChildElement(doc, "resources");
			
			Node confNameNode = XmlUtil.findFirstNodeByAttribute(resources.getChildNodes(), "name", "main_conf_name");
			confNameNode.setTextContent(model.getInformation().getTitle());
			
			Node confWebsite = XmlUtil.findFirstNodeByAttribute(resources.getChildNodes(), "name", "main_website_url");
			confWebsite.setTextContent(model.getInformation().getWebsiteUrl());
			
			return new FileEntry("res/values/strings.xml", XmlUtil.getXml(doc));
		} catch (IOException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		} catch (ParserConfigurationException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		} catch (SAXException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		} catch (TransformerException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		}
	}

	private FileEntry createAdoutConferenceWebPage() throws TemplateAdapterException {
		// Get webpage from asets
		File webpage = new File(sourcesPath + File.separator + "assets" + File.separator + "about.html");
		if (!webpage.exists()) {
			throw new TemplateAdapterException("Webpage 'about.html' is not exists in project sources");
		}
		
		try {
			String webPageContent = FileUtils.readFileToString(webpage);
			Document html = Jsoup.parse(webPageContent);
			String bodyText = html.body().text();
			FileEntry file = new FileEntry("assets/about.html", webPageContent.replace(bodyText, model.getInformation().getAbout()));
			return file;
		} catch (IOException e) {
			throw new TemplateAdapterException("Erorr when reading 'about.html' file in project sources", e);
		}
	}

	public void setSourcesPath(String sourcesPath) {
		this.sourcesPath = sourcesPath;
	}
}