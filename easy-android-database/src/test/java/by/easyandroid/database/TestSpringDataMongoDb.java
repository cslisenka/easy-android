package by.easyandroid.database;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import by.easyandroid.model.User;

public class TestSpringDataMongoDb {

	private static final String F_LOGIN = "login";
	private static final String F_PASSWORD = "password";
	private static final String COLLECTION = "users";
	private static final String USERNAME = "kslisenko";

	@Test
	public void testMongoCrud() {
		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("test-database-context.xml");

		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		User user = new User();
		user.setLogin(USERNAME);
		user.setPassword("test");

		// save
		mongoOperation.save(user, COLLECTION);

		// find
		User savedUser = mongoOperation.findOne(
				new Query(Criteria.where(F_LOGIN).is(USERNAME)), User.class, COLLECTION);

		Assert.assertNotNull(savedUser);
		// TODO logger
		System.out.println("savedUser : " + savedUser);

		// update
		mongoOperation.updateMulti(
				new Query(Criteria.where(F_LOGIN).is(USERNAME)),
				Update.update(F_PASSWORD, "new password"), COLLECTION);

		// find
		User updatedUser = mongoOperation.findOne(
				new Query(Criteria.where(F_LOGIN).is(USERNAME)), User.class, COLLECTION);

		Assert.assertNotNull(updatedUser);
		Assert.assertEquals("new password", updatedUser.getPassword());
		// TODO logger
		System.out.println("updatedUser : " + updatedUser);

		// delete
		mongoOperation.remove(new Query(Criteria.where("login").is(USERNAME)), COLLECTION);

		// List
		List<User> listUser = mongoOperation.findAll(User.class, COLLECTION);
		Assert.assertEquals(0, listUser.size());
		
		// TODO logger
		System.out.println("Number of user = " + listUser.size());
	}
}