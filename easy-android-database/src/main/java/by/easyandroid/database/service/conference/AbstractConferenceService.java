package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.model.Identity;

public abstract class AbstractConferenceService<T extends Identity> extends AbstractGenericService<T> {

	protected ApplicationInstanceService applicationInstanceService;	
	
	public AbstractConferenceService(MongoOperations mongo, Class<T> type, String collection) {
		super(mongo, type, collection);
	}

	public ApplicationInstanceService getApplicationInstanceService() {
		return applicationInstanceService;
	}

	public void setApplicationInstanceService(
			ApplicationInstanceService applicationInstanceService) {
		this.applicationInstanceService = applicationInstanceService;
	}
}