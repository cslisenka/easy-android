package by.easyandroid.database.service.conference;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Reporter;

public class ReporterService extends AbstractConferenceService<Reporter> {

	private static final String REPORTER = "reporter";

	public ReporterService(MongoOperations mongo) {
		super(mongo, Reporter.class, REPORTER);
	}
	
	@Override
	protected List<Reporter> getApplicationRelationList(ApplicationInstance application) {
		return application.getModel().getReporters();
	}
}