package by.easyandroid.database.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.Identity;

public abstract class AbstractGenericService<T extends Identity> {

	protected MongoOperations mongo;
	
	protected Class<T> type;
	protected String collection;

	public AbstractGenericService(MongoOperations mongo, Class<T> type, String collection) {
		this.mongo = mongo;
		this.type = type;
		this.collection = collection;
	}
	
	public void add(T obj) throws DatabaseServiceException {
		mongo.save(obj, collection);
	}
	
	public List<T> getAll() throws DatabaseServiceException {
		return mongo.findAll(type, collection);
	}

	public Class<T> getType() {
		return type;
	}

	public String getCollection() {
		return collection;
	}
}