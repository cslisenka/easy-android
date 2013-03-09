package by.easyandroid.database.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

public class UserService {

	private static final String USER = "user";
	
	private MongoOperations mongo;
	
	public UserService(MongoOperations mongo) {
		this.mongo = mongo;
	}

	public void add(User user) throws DatabaseServiceException {
		// Check if user with this login and password already exists
		if (get(user.getLogin(), user.getPassword()) != null) {
			throw new DatabaseServiceException("User with specified login and password already exists");	
		}
		
		mongo.save(user, USER);	
	}
	
	public List<User> getAll() {
		return mongo.findAll(User.class, USER);
	}
	
	public User get(String login, String password) {
		Query q = new Query();
		q.addCriteria(Criteria.where("login").is(login));
		q.addCriteria(Criteria.where("password").is(password));
		return mongo.findOne(q, User.class, USER);
	}
}