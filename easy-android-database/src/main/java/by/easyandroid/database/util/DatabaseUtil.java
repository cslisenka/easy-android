package by.easyandroid.database.util;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.model.Identity;

public class DatabaseUtil {

	/**
	 * Collects ids of given objects
	 * @return
	 */
	public static List<String> getIds(List<? extends Identity> objects) {
		List<String> ids = new ArrayList<String>();
		
		for (Identity obj : objects) {
			String id = obj.getId();
			if (id != null) {
				ids.add(id);
			}
		}
		
		return ids;
	}
}