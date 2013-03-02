package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.model.Section;
import by.easyandroid.template.conference.util.AndroidUtil;

public class ReportService extends AbstractEntityService<Report> {

	private static final String TITLE = "title";
	private static final String ID = "id";

	public ReportService(Context context) {
		super(context);
	}

	@Override
	public List<Report> getAll() {
		Document xml = AndroidUtil.readXmlAsset(context, DATA_XML);
		List<Report> result = new ArrayList<Report>();
		
		NodeList reports = xml.getElementsByTagName("report");
		for (int i = 0; i < reports.getLength(); i++) {
			Report report = parseReport(reports.item(i), i);
			
			// Fake reporter
			Reporter r = new Reporter();
			r.setName("Иванов И.И.");
			r.setDescription("Ведущий специалист в своей области");
			report.setReporter(r);
			
			// Fake section
			Section s = new Section();
			s.setName("Секция А");
			report.setSection(s);
			
			result.add(report);
		}
		
		return result;
	}

	@Override
	public Report getById(long id) {
		// TODO fake reporter now
		return createReport("Какой-то доклад");
	}
	
	private Report parseReport(Node reportItem, int i) {
		Report report = new Report();
		report.setId(getElementAttr(reportItem, ID));
		report.setTitle(getChildElementText(reportItem, TITLE));
		report.setDesctiption(getChildElementText(reportItem, "description"));
		parseReportDate(reportItem, report);
		return report;
	}

	private void parseReportDate(Node reportItem, Report report) {
		Node date = getChildElement(reportItem, "date");
		int year = getElementIntAttr(date, "year");
		int month = getElementIntAttr(date, "month");
		int day = getElementIntAttr(date, "day");
		int hour = getElementIntAttr(date, "hours");
		int minute = getElementIntAttr(date, "minutes");
		report.setTime(new Date(year, month, day, hour, minute));
	}
	
	private Report createReport(String title) {
		Report report = new Report();
		report.setId("1");
		report.setTitle(title);
		
		Reporter r = new Reporter();
		r.setName("Иванов И.И.");
		r.setDescription("Ведущий специалист в своей области");
		report.setReporter(r);
		report.setTime(new Date(2012, 10, 30, 10, 0));
		
		Section s = new Section();
		s.setName("Секция А");
		report.setSection(s);
		
		report.setDesctiption("Описание какого-то доклада, например очень длинный текст.");
		
		return report;
	}
	
	protected Node getChildElement(Node element, String childNodeName) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			Node child = element.getChildNodes().item(i);
			if (child.getNodeName().equals(childNodeName)) {
				return child;
			}
		}
		
		return null;
	}	
	
	protected String getChildElementText(Node element, String childNodeName) {
		Node child = getChildElement(element, childNodeName);
		
		if (child != null) {
			return child.getTextContent();
		}
		
		return null;
	}

	protected String getElementAttr(Node element, String attrName) {
		for (int i = 0; i < element.getAttributes().getLength(); i++) {
			Node child = element.getAttributes().item(i);
			if (child.getNodeName().equals(attrName)) {
				return child.getNodeValue();
			}
		}
		
		return null;
	}
	
	protected int getElementIntAttr(Node element, String attrName) {
		String attrValue = getElementAttr(element, attrName);
		
		if (attrValue != null) {
			return Integer.parseInt(attrValue);
		}
		
		return 0;
	}	
}