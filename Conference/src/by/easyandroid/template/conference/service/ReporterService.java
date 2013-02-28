package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.template.conference.model.Reporter;

public class ReporterService {

	public List<Reporter> getReporters() {
		List<Reporter> result = new ArrayList<Reporter>();
		
		// TODO get reorters from xml file
		result.add(addReporter("Иванов И.И."));
		result.add(addReporter("Петров И.И."));
		result.add(addReporter("Сидоров И.И."));
		
		return result;
	}
	
	private Reporter addReporter(String name) {
		Reporter reporter = new Reporter();
		reporter.setId("1");
		reporter.setName(name);
		reporter.setDescription("Ведущий специалист в своей области, основатель первого сообщества.");
		
		return reporter;
	}
}