package by.easyandroid.template.conference.service;

import java.util.Date;

import org.w3c.dom.Node;

import android.content.Context;
import by.easyandroid.template.conference.model.Category;
import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.model.Section;
import by.easyandroid.template.conference.util.XmlUtil;

public class ReportService extends AbstractFilterSupportEntityService<Report> {

	private static final String REPORT = "report";
	
	private static final String DATE = "date";
	private static final String DESCRIPTION = "description";
	private static final String TITLE = "title";
	
	private static final String MINUTES = "minutes";
	private static final String HOURS = "hours";
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";	

	private ReporterService reporterService;
	private SectionService sectionService;
	private CategoryService categoryService;
	
	public ReportService(Context context) {
		super(context, REPORT);
	}

	public void setReporterService(ReporterService reporterService) {
		this.reporterService = reporterService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	protected Report parseItem(Node reportItem) {
		Report report = new Report();
		report.setId(XmlUtil.getElementAttr(reportItem, ID));
		report.setTitle(XmlUtil.getChildElementText(reportItem, TITLE));
		report.setDesctiption(XmlUtil.getChildElementText(reportItem, DESCRIPTION));
		report.setTime(parseReportDate(reportItem, report));
		
		Reporter reporter = reporterService.getById(XmlUtil.getElementIntAttr(reportItem, REPORTER));
		report.setReporter(reporter);
		
		Section section = sectionService.getById(XmlUtil.getElementIntAttr(reportItem, SECTION));
		report.setSection(section);
		
		Category category = categoryService.getById(XmlUtil.getElementIntAttr(reportItem, CATEGORY));
		report.setCategory(category);
		
		return report;
	}

	// TODO to report util
	private Date parseReportDate(Node reportItem, Report report) {
		Node date = XmlUtil.getChildElement(reportItem, DATE);
		int year = XmlUtil.getElementIntAttr(date, YEAR);
		int month = XmlUtil.getElementIntAttr(date, MONTH);
		int day = XmlUtil.getElementIntAttr(date, DAY);
		int hour = XmlUtil.getElementIntAttr(date, HOURS);
		int minute = XmlUtil.getElementIntAttr(date, MINUTES);
		return new Date(year, month, day, hour, minute);
	}
}