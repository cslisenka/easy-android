package by.easyandroid.database.service.conference;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Section;

public class SectionService extends AbstractConferenceService<Section> {

	private static final String SECTION = "section";

	public SectionService(MongoOperations mongo) {
		super(mongo, Section.class, SECTION);
	}
	
	@Override
	protected List<Section> getApplicationRelationList(ApplicationInstance application) {
		return application.getModel().getSections();
	}
}