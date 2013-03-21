package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Section;

public class SectionService extends AbstractConferenceService<Section> {

	private static final String SECTION = "section";

	public SectionService(MongoOperations mongo) {
		super(mongo, Section.class, SECTION);
	}
	
	// TODO unit-test
	public void add(Section section, ApplicationInstance application) throws DatabaseServiceException {
		add(section);
		
		// Adding section to conference model
		application.getModel().getSections().add(section);
		applicationInstanceService.save(application);
	}
}