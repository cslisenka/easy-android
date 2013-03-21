package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Category;

public class CategoryService extends AbstractConferenceService<Category> {

	private static final String CATEGORY = "category";

	public CategoryService(MongoOperations mongo) {
		super(mongo, Category.class, CATEGORY);
	}
	
	// TODO unit-test
	public void add(Category category, ApplicationInstance application) throws DatabaseServiceException {
		add(category);
		
		// Adding section to conference model
		application.getModel().getCategories().add(category);
		applicationInstanceService.save(application);
	}
}