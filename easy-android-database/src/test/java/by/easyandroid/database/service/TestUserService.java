package by.easyandroid.database.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.User;

public class TestUserService extends AbstractGenericServiceTest<UserService, User> {

	@Test
	public void testFindByLoginAndPw() throws DatabaseServiceException {
		User user = createTestEntity();
		service.add(user);
		
		User findedUser = service.get("test", "testpw");
		assertTestEntity(findedUser);	
	}

	@Test(expected = DatabaseServiceException.class)
	public void addExistedUser() throws DatabaseServiceException {
		User user = createTestEntity();
		service.add(user);
		service.add(user);
	}
	
	@Override
	protected void assertTestEntity(User findedUser) {
		Assert.assertEquals("test", findedUser.getLogin());
		Assert.assertEquals("testpw", findedUser.getPassword());
		Assert.assertEquals("testemail", findedUser.getEmail());
	}

	@Override
	protected User createTestEntity() {
		User user = new User();
		user.setLogin("test");
		user.setPassword("testpw");
		user.setEmail("testemail");
		return user;
	}

	@Override
	protected UserService createService(MongoOperations mongo) {
		return new UserService(mongo);
	}
}