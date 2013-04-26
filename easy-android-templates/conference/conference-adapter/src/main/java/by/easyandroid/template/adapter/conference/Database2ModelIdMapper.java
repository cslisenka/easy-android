package by.easyandroid.template.adapter.conference;

import java.util.HashMap;
import java.util.Map;

import by.easyandroid.model.Identity;

public class Database2ModelIdMapper {

	private Map<String, String> databaseIds2NumericIds = new HashMap<String, String>();
	private int lastId = 0;
	
	public String getId(String databaseId) {
		if (databaseIds2NumericIds.containsKey(databaseId)) {
			return databaseIds2NumericIds.get(databaseId);
		}
		
		lastId++;
		String stringedId = "" + lastId;
		databaseIds2NumericIds.put(databaseId, stringedId);
		return stringedId;
	}
	
	public String getId(Identity object) {
		return getId(object.getId());
	}
}