package by.easyandroid.database.service;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationTemplate;

public class TemplateService extends AbstractGenericService<ApplicationTemplate> {

	private static final String APPLICATION_TEMPLATE = "applicationTemplate";

	public TemplateService(MongoOperations mongo) {
		super(mongo, ApplicationTemplate.class, APPLICATION_TEMPLATE);
	}
}