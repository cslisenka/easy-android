package by.easyandroid.database.service;

import java.sql.DatabaseMetaData;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

public class TestUserService {

	private UserService service;
	
	@Before
	public void setUp() {
		// TODO include production config into test config
		ApplicationContext ctx = new GenericXmlApplicationContext("test-mongo-config.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		service = new UserService(mongoOperation);
		
		// Clear users
		mongoOperation.dropCollection(User.class);
	}
	
	@Test
	public void testAdd() throws DatabaseServiceException {
		User user = createTestUser();
		service.add(user);
		
		List<User> users = service.getAll();
		Assert.assertEquals(1, users.size());
		assertTestUser(users.get(0));
	}
	
	@Test
	public void testFindByLoginAndPw() throws DatabaseServiceException {
		User user = createTestUser();
		service.add(user);
		
		User findedUser = service.get("test", "testpw");
		assertTestUser(findedUser);	
	}

	@Test(expected = DatabaseServiceException.class)
	public void addExistedUser() throws DatabaseServiceException {
		User user = createTestUser();
		service.add(user);
		service.add(user);
	}
	
	private void assertTestUser(User findedUser) {
		Assert.assertEquals("test", findedUser.getLogin());
		Assert.assertEquals("testpw", findedUser.getPassword());
		Assert.assertEquals("testemail", findedUser.getEmail());
	}

	private User createTestUser() {
		User user = new User();
		user.setLogin("test");
		user.setPassword("testpw");
		user.setEmail("testemail");
		return user;
	}
}