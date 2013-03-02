package by.easyandroid.template.conference.filter;

import java.util.HashSet;
import java.util.Set;

public class FilterSet<E> {

	private Set<IFilter<E>> filters = new HashSet<IFilter<E>>();
	
	public void addFilter(IFilter<E> filter) {
		if (filter != null) {
			filters.add(filter);
		}
	}
	
	public Set<IFilter<E>> getFilters() {
		return filters;
	}
}