package by.easyandroid.model.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import by.easyandroid.model.User;

public class ModelUtilTest {

	@Test
	public void testRemoveById() {
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setId("111");
		users.add(u1);

		User u2 = new User();
		u2.setId("222");
		users.add(u2);
		
		User u3 = new User();
		u3.setId("333");
		users.add(u3);
		
		ModelUtil.removeById(users, "111");
		Assert.assertEquals(2, users.size());
		Assert.assertFalse(users.contains(u1));
	}
}