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
	
	/**
	 * Removes objects from list by specified id
	 * @param list
	 * @param id
	 */
	public static <T extends Identity> void replaceById(List<T> list, T objToReplace ) {
		for (int i = 0; i < list.size(); i++) {
			if (objToReplace.getId().equals(list.get(i).getId())) {
				list.set(i, objToReplace);
				return;
			}
		}
	}
}