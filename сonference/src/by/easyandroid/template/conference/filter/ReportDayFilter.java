package by.easyandroid.template.conference.filter;

import java.util.Date;

import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.util.DateUtil;

public class ReportDayFilter implements IFilter<Report> {

	private Date day;
	
	public ReportDayFilter(Date day) {
		this.day = day;
	}

	@Override
	public boolean isMatch(Report report) {
		return DateUtil.isSameDay(report.getTime(), day);
	}
}