package by.easyandroid.database.util;

import org.springframework.data.mongodb.core.MongoOperations;

public class TestDatabaseUtil {

	private static final String SYSTEM_COLLECTION = "system.indexes";

	public static void cleanCurentDatabase(MongoOperations mongo) {
		for (String collection : mongo.getCollectionNames()) {
			if (!collection.equals(SYSTEM_COLLECTION)) {
				mongo.dropCollection(collection);
			}
		}
	}
}