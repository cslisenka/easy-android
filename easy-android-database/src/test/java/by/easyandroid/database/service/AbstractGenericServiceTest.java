package by.easyandroid.database.service;

import java.util.ArrayList;
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
	public void testAddAndGetAll() throws DatabaseServiceException {
		T entity = createTestEntity();
		service.add(entity);
		
		List<T> objects = service.getAll();
		Assert.assertEquals(1, objects.size());
		assertTestEntity(objects.get(0));
		Assert.assertNotNull(objects.get(0).getId());
	}
	
	@Test
	public void testCopy() throws DatabaseServiceException {
		T entity = createTestEntity();
		service.add(entity);
		
		T copy = service.copy(entity.getId());
		assertTestEntity(copy);
		Assert.assertNotSame(copy.getId(), entity.getId());
		Assert.assertEquals(2, service.getAll().size());
	}
	
	@Test(expected = DatabaseServiceException.class)
	public void testCopyNonExistingId() throws DatabaseServiceException {
		service.copy("wrong");
	}	
	
	@Test
	public void testCopyList() throws DatabaseServiceException {
		T entity1 = createTestEntity();
		T entity2 = createTestEntity();
		service.add(entity1);
		service.add(entity2);
		
		Assert.assertEquals(2, service.getAll().size());
		
		List<String> ids = new ArrayList<String>();
		ids.add(entity1.getId());
		ids.add(entity2.getId());
		
		List<T> copied = service.copy(ids);
		Assert.assertEquals(2, copied.size());
		assertTestEntity(entity1);
		assertTestEntity(entity2);
		
		// Check that copied entities has different ids
		for (T copiedEntity : copied) {
			Assert.assertFalse(ids.contains(copiedEntity.getId()));
		}
		
		Assert.assertEquals(4, service.getAll().size());
	}	
}