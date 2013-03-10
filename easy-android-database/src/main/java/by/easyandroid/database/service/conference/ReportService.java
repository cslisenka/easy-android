package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.model.conference.Report;

public class ReportService extends AbstractGenericService<Report> {

	private static final String REPORT = "report";

	public ReportService(MongoOperations mongo) {
		super(mongo, Report.class, REPORT);
	}
}