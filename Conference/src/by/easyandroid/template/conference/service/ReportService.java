package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.easyandroid.template.conference.model.Report;
import by.easyandroid.template.conference.model.Reporter;
import by.easyandroid.template.conference.model.Section;

public class ReportService extends AbstractEntityService<Report> {

	@Override
	public List<Report> getAll() {
		List<Report> result = new ArrayList<Report>();
		
		// TODO get reports from xml file
		result.add(addReport("Первый доклад"));
		result.add(addReport("Первый доклад 2"));
		result.add(addReport("Первый доклад 3"));
		result.add(addReport("Первый доклад 4"));
		result.add(addReport("Первый доклад 5"));
		result.add(addReport("Первый доклад 6"));
		result.add(addReport("Первый доклад"));
		result.add(addReport("Первый доклад 2"));
		result.add(addReport("Первый доклад 3"));
		result.add(addReport("Первый доклад 4"));
		result.add(addReport("Первый доклад 5"));
		result.add(addReport("Первый доклад 6"));		

		return result;
	}
	
	private Report addReport(String title) {
		Report report = new Report();
		report.setId("1");
		report.setTitle(title);
		
		Reporter r = new Reporter();
		r.setName("Иванов И.И.");
		report.setReporter(r);
		report.setTime(new Date(2012, 10, 30, 10, 0));
		
		Section s = new Section();
		s.setName("Секция А");
		report.setSection(s);
		
		return report;
	}
}