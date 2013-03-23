package by.easyandroid.database.service.conference;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.Category;

public class CategoryService extends AbstractConferenceService<Category> {

	private static final String CATEGORY = "category";

	public CategoryService(MongoOperations mongo) {
		super(mongo, Category.class, CATEGORY);
	}
	
	@Override
	protected List<Category> getApplicationRelationList(ApplicationInstance application) {
		return application.getModel().getCategories();
	}
}