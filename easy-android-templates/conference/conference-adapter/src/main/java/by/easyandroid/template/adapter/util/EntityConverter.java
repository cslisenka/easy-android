package by.easyandroid.template.adapter.util;

import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.template.adapter.conference.Database2ModelIdMapper;

public class EntityConverter {

	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String ID = "id";
	
	private static final String DATE = "date";
	private static final String MINUTES = "minutes";
	private static final String HOURS = "hours";
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";		

	private Database2ModelIdMapper mapper;
	
	public EntityConverter(Database2ModelIdMapper mapper) {
		this.mapper = mapper;
	}
	
	public Element toXml(Category category, Document doc) {
		Element categoryElement = doc.createElement("category");
		
		categoryElement.setAttribute(ID, mapper.getId(category));
		categoryElement.appendChild(XmlUtil.appendTextNode(NAME, category.getName(), doc));		
		return categoryElement;
	}	
	
	public Element toXml(Section section, Document doc) {
		Element sectionElement = doc.createElement("section");
		
		sectionElement.setAttribute(ID, mapper.getId(section));
		sectionElement.appendChild(XmlUtil.appendTextNode(NAME, section.getName(), doc));		
		return sectionElement;
	}
	
	public Element toXml(Reporter reporter, Document doc) {
		Element reporterElement = doc.createElement("reporter");
		
		reporterElement.setAttribute(ID, mapper.getId(reporter));
		reporterElement.appendChild(XmlUtil.appendTextNode(NAME, reporter.getName(), doc));
		reporterElement.appendChild(XmlUtil.appendTextNode(DESCRIPTION, reporter.getDescription(), doc));
		reporterElement.appendChild(XmlUtil.appendTextNode("email", reporter.getEmail(), doc));
		reporterElement.appendChild(XmlUtil.appendTextNode("company", reporter.getCompany(), doc));	
		reporterElement.appendChild(XmlUtil.appendTextNode("position", reporter.getPosition(), doc));	
		
		return reporterElement;
	}
	
	public Element toXml(Report report, Document doc) {
		Element reportElement = doc.createElement("report");
		
		reportElement.setAttribute(ID, mapper.getId(report));
		reportElement.setAttribute("category", mapper.getId(report.getCategory()));
		reportElement.setAttribute("reporter", mapper.getId(report.getReporter()));
		reportElement.setAttribute("section", mapper.getId(report.getSection()));
		
		reportElement.appendChild(XmlUtil.appendTextNode("title", report.getTitle(), doc));
		reportElement.appendChild(XmlUtil.appendTextNode(DESCRIPTION, report.getDescription(), doc));
		
		reportElement.appendChild(toXml(report.getTime(), doc));
		
		return reportElement;
	}
	
	public static Element toXml(Date date, Document doc) {
		Element xmlDate = doc.createElement(DATE);
		xmlDate.setAttribute(YEAR, date.getYear() + "");
		xmlDate.setAttribute(MONTH, date.getMonth() + "");
		xmlDate.setAttribute(DAY, date.getDate() + "");
		xmlDate.setAttribute(HOURS, date.getHours() + "");
		xmlDate.setAttribute(MINUTES, date.getMinutes() + "");
		return xmlDate;
	}
	
	public static Date toDate(Node reportItem) {
		Node date = XmlUtil.getChildElement(reportItem, DATE);
		int year = XmlUtil.getElementIntAttr(date, YEAR);
		int month = XmlUtil.getElementIntAttr(date, MONTH);
		int day = XmlUtil.getElementIntAttr(date, DAY);
		int hour = XmlUtil.getElementIntAttr(date, HOURS);
		int minute = XmlUtil.getElementIntAttr(date, MINUTES);
		return new Date(year, month, day, hour, minute);
	}

	public Database2ModelIdMapper getMapper() {
		return mapper;
	}
}