package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Reporter;

public class ReporterService extends AbstractConferenceService<Reporter> {

	private static final String REPORTER = "reporter";

	public ReporterService(MongoOperations mongo) {
		super(mongo, Reporter.class, REPORTER);
	}
	
	// TODO unit-test
	public void add(Reporter category, ApplicationInstance application) throws DatabaseServiceException {
		add(category);
		
		// Adding section to conference model
		application.getModel().getReporters().add(category);
		applicationInstanceService.save(application);
	}	
}