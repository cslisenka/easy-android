package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.model.conference.Reporter;

public class ReporterService extends AbstractGenericService<Reporter> {

	private static final String REPORTER = "reporter";

	public ReporterService(MongoOperations mongo) {
		super(mongo, Reporter.class, REPORTER);
	}
}