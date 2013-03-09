package by.easyandroid.database.service;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

public class UserService extends AbstractGenericService<User> {

	private static final String USER = "user";
	
	public UserService(MongoOperations mongo) {
		super(mongo, User.class, USER);
	}

	@Override
	public void add(User user) throws DatabaseServiceException {
		// Check if user with this login and password already exists
		if (get(user.getLogin(), user.getPassword()) != null) {
			throw new DatabaseServiceException("User with specified login and password already exists");	
		}
		
		super.add(user);
	}
	
	public User get(String login, String password) {
		Query q = new Query();
		q.addCriteria(Criteria.where("login").is(login));
		q.addCriteria(Criteria.where("password").is(password));
		return mongo.findOne(q, User.class, USER);
	}
}