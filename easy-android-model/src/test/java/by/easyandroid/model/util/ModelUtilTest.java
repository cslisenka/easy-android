package by.easyandroid.model.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.model.User;

public class ModelUtilTest {

	private List<User> users;
	private User u1;
	
	@Before
	public void setUp() {
		users = new ArrayList<User>();
		u1 = new User();
		u1.setId("111");
		users.add(u1);

		User u2 = new User();
		u2.setId("222");
		users.add(u2);
		
		User u3 = new User();
		u3.setId("333");
		users.add(u3);
	}
	
	@Test
	public void testRemoveById() {
		ModelUtil.removeById(users, "111");
		Assert.assertEquals(2, users.size());
		Assert.assertFalse(users.contains(u1));
	}
	
	@Test
	public void testReplaceById() {
		User newUser = new User();
		newUser.setId(users.get(0).getId());
		newUser.setLogin("anotherLogin");
		ModelUtil.replaceById(users, newUser);
		Assert.assertEquals(3, users.size());
		Assert.assertEquals("anotherLogin", users.get(0).getLogin());
	}
}