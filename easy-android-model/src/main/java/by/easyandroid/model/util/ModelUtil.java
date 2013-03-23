package by.easyandroid.model.util;

import java.util.List;

import by.easyandroid.model.Identity;

public class ModelUtil {

	/**
	 * Removes objects from list by specified id
	 * @param list
	 * @param id
	 */
	public static void removeById(List<? extends Identity> list, String id) {
		for (Identity obj : list) {
			if (id.equals(obj.getId())) {
				list.remove(obj);
				return;
			}
		}
	}
}