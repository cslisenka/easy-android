package by.easyandroid.database.service.conference;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.Identity;
import by.easyandroid.model.util.ModelUtil;

public abstract class AbstractConferenceService<T extends Identity> extends AbstractGenericService<T> {

	protected ApplicationInstanceService applicationInstanceService;	
	
	public AbstractConferenceService(MongoOperations mongo, Class<T> type, String collection) {
		super(mongo, type, collection);
	}
	
	// TODO unit-test
	public void delete(String id, ApplicationInstance application) {
		// TODO check if some report relates to this object and do not allow delete object before report
		// is not deleted, throw exception
		
		// Remove from model
		ModelUtil.removeById(getApplicationRelationList(application), id);
		applicationInstanceService.save(application);
		
		delete(id);
	}
	
	// TODO unit-test
	public void add(T obj, ApplicationInstance application) throws DatabaseServiceException {
		add(obj);
		
		// Adding section to conference model
		getApplicationRelationList(application).add(obj);
		applicationInstanceService.save(application);
	}	

	public ApplicationInstanceService getApplicationInstanceService() {
		return applicationInstanceService;
	}

	public void setApplicationInstanceService(ApplicationInstanceService applicationInstanceService) {
		this.applicationInstanceService = applicationInstanceService;
	}
	
	/**
	 * Conference db service classes must return model part, which they connected
	 * @param model
	 */
	protected abstract List<T> getApplicationRelationList(ApplicationInstance model);
}