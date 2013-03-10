package by.easyandroid.database.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.ApplicationInstance;
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
	public void testAddExistedUser() throws DatabaseServiceException {
		User user = createTestEntity();
		service.add(user);
		service.add(user);
	}
	
	/**
	 * Testing how spring data saves relations
	 * @throws DatabaseServiceException
	 */
	@Test
	public void testSaveApplicationInstances() throws DatabaseServiceException {
		User user = createTestEntity();
		ApplicationInstance instance = new ApplicationInstance();
		instance.setName("my app");
		user.getApplications().add(instance);
		
		ApplicationInstanceService appInstServ = new ApplicationInstanceService(mongoOperation);
		appInstServ.add(instance);
		
		service.add(user);

		User user2 = service.get(user.getLogin(), user.getPassword());
		assertTestEntity(user2);
		Assert.assertTrue(user2.getApplications().size() > 0);
		Assert.assertNotNull(user2.getApplications().get(0));
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