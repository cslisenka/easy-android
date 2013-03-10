package by.easyandroid.database.service.conference;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.model.conference.Category;

public class CategoryService extends AbstractGenericService<Category> {

	private static final String CATEGORY = "category";

	public CategoryService(MongoOperations mongo) {
		super(mongo, Category.class, CATEGORY);
	}
}