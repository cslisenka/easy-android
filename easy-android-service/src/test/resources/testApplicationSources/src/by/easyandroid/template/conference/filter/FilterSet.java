package by.easyandroid.template.conference.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FilterSet<E> {

	private Map<String, IFilter<E>> filters = new HashMap<String, IFilter<E>>();
	
	public void addFilter(String filterName, IFilter<E> filter) {
		if (filter != null) {
			filters.put(filterName, filter);
		}
	}
	
	public Collection<IFilter<E>> getFilters() {
		return filters.values();
	}
}