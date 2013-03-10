package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.model.conference.Section;

public class SectionService extends AbstractGenericService<Section> {

	private static final String SECTION = "section";

	public SectionService(MongoOperations mongo) {
		super(mongo, Section.class, SECTION);
	}
}