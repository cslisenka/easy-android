package by.easyandroid.template.conference.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.easyandroid.template.conference.model.Report;

public class DateUtil {

	public static List<Date> getReportDays(List<Report> reports) {
		Set<Date> datesSet = new HashSet<Date>();
		
		for (Report oneReport : reports) {
			Date reportDate = oneReport.getTime();
			Date reportDay = new Date(reportDate.getYear(), reportDate.getMonth(), reportDate.getDate());
			datesSet.add(reportDay);
		}
		
		return new ArrayList<Date>(datesSet);
	}
	
	public static List<DateSpinnerProxy> getReportDaysProxy(List<Report> reports) {
		List<Date> days = getReportDays(reports);
		List<DateSpinnerProxy> proxyDates = new ArrayList<DateSpinnerProxy>();
		
		for (Date oneDay : days) {
			proxyDates.add(new DateSpinnerProxy(oneDay));
		}
		
		return proxyDates;
	}
}