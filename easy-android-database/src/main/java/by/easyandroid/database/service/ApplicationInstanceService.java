package by.easyandroid.database.service;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationInstance;

public class ApplicationInstanceService extends AbstractGenericService<ApplicationInstance> {

	private static final String APPLICATION_INSTANCE = "applicationInstance";

	public ApplicationInstanceService(MongoOperations mongo) {
		super(mongo, ApplicationInstance.class, APPLICATION_INSTANCE);
	}
}