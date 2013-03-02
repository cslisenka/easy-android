package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.template.conference.model.Reporter;

public class ReporterService extends AbstractEntityService<Reporter> {

	@Override
	public List<Reporter> getAll() {
		List<Reporter> result = new ArrayList<Reporter>();
		
		// TODO get reorters from xml file
		result.add(createReporter("Иванов И.И."));
		result.add(createReporter("Петров И.И."));
		result.add(createReporter("Сидоров И.И."));
		
		return result;
	}
	
	@Override
	public Reporter getById(long id) {
		// TODO Fake reporter now!
		return createReporter("Иванов И.И.");
	}
	
	private Reporter createReporter(String name) {
		Reporter reporter = new Reporter();
		reporter.setId("1");
		reporter.setName(name);
		reporter.setDescription("Ведущий специалист в своей области, основатель первого сообщества.");
		reporter.setCompany("SomeCompany");
		reporter.setEmail("ivanov@somecompany.com");
		reporter.setPosition("Ведущий специалист");
		
		return reporter;
	}	
}