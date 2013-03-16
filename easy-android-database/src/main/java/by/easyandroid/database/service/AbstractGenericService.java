package by.easyandroid.database.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.Identity;

// TODO to think if use spring repositories 
// http://static.springsource.org/spring-data/data-mongo/docs/1.0.0.M5/reference/html/

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
	
	/**
	 * Copies object by it's object id
	 * @param id MongoDB id of copied object
	 * @return new copied object
	 */
	public T copy(String id) throws DatabaseServiceException {
		// TODO implement method with Section object as a parameter
		T entity = mongo.findById(id, type, collection);
		if (entity == null) {
			throw new DatabaseServiceException("Can not find entity with id = '" + id + "' to make a copy");
		}
		
		entity.setId(null);
		add(entity);
		
		return entity;
	}
	
	/**
	 * Copies many object by it's object id
	 * @param ids MongoDB ids of copied objects
	 * @return list with new copied object
	 */
	public List<T> copy(List<String> ids) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").in(ids));
		List<T> entities = mongo.find(query, type, collection);
		for (T oneEntity : entities) {
			oneEntity.setId(null);
		}
		
		mongo.insert(entities, collection);
		return entities;
	}
	
	// TODO unit-test
	public void save(T entity) {
		mongo.save(entity, collection);
	}
	
	public Class<T> getType() {
		return type;
	}

	public String getCollection() {
		return collection;
	}
}