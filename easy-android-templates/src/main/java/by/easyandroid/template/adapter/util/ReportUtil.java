package by.easyandroid.template.adapter.util;

import java.util.Date;

import org.w3c.dom.Node;

public class ReportUtil {

	private static final String DATE = "date";
	private static final String MINUTES = "minutes";
	private static final String HOURS = "hours";
	private static final String DAY = "day";
	private static final String MONTH = "month";
	private static final String YEAR = "year";		
	
	public static Date parseReportDate(Node reportItem) {
		Node date = XmlUtil.getChildElement(reportItem, DATE);
		int year = XmlUtil.getElementIntAttr(date, YEAR);
		int month = XmlUtil.getElementIntAttr(date, MONTH);
		int day = XmlUtil.getElementIntAttr(date, DAY);
		int hour = XmlUtil.getElementIntAttr(date, HOURS);
		int minute = XmlUtil.getElementIntAttr(date, MINUTES);
		return new Date(year, month, day, hour, minute);
	}
}
