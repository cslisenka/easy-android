package by.easyandroid.database.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.Identity;

public abstract class AbstractGenericServiceTest<S extends AbstractGenericService<T>, T extends Identity> {

	protected MongoOperations mongoOperation;
	
	protected S service;
	
	protected abstract S createService(MongoOperations mongo);
	
	protected abstract T createTestEntity();
	
	protected abstract void assertTestEntity(T object);
	
	@Before
	public void setUp() {
		// TODO include production config into test config
		ApplicationContext ctx = new GenericXmlApplicationContext("test-database-context.xml");
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		service = createService(mongoOperation);
		
		// Clear users
		mongoOperation.dropCollection(service.getCollection());
	}
	
	@Test
	public void testAdd() throws DatabaseServiceException {
		T entity = createTestEntity();
		service.add(entity);
		
		List<T> objects = service.getAll();
		Assert.assertEquals(1, objects.size());
		assertTestEntity(objects.get(0));
		Assert.assertNotNull(objects.get(0).getId());
	}
}