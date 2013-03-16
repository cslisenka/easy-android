package by.easyandroid.database.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.easyandroid.database.service.AbstractGenericService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.model.Identity;

public class RelationSavingCopier<T extends Identity> {

	private Map<String, T> relationMap = new HashMap<String, T>();
	private AbstractGenericService<T> service;
	
	public RelationSavingCopier(AbstractGenericService<T> service) {
		this.service = service;
	}
	
	public T copyOrRelate(T object) throws DatabaseServiceException {
		if (!relationMap.containsKey(object.getId())) {
			T newObject = service.copy(object.getId());
			relationMap.put(object.getId(), newObject);
			return newObject;
		} else {
			return relationMap.get(object.getId());
		}
	}
	
	public List<T> getCopiedObjects() {
		return new ArrayList<T>(relationMap.values());
	}
}