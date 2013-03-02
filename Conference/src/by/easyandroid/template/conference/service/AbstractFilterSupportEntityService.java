package by.easyandroid.template.conference.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.Context;
import by.easyandroid.template.conference.filter.IFilter;
import by.easyandroid.template.conference.model.Identity;

public abstract class AbstractFilterSupportEntityService<E extends Identity> extends AbstractEntityService<E> {

	public AbstractFilterSupportEntityService(Context context, String itemNodeName) {
		super(context, itemNodeName);
	}

	public List<E> getAll(Set<IFilter<E>> filters) {
		List<E> result = super.getAll();
		
		// Apply filters
		for (IFilter<E> oneFilter : filters) {
			result = doFiltering(oneFilter, result);
		}
		
		return result;
	}

	private List<E> doFiltering(IFilter<E> oneFilter, List<E> result) {
		List<E> filteredResult = new ArrayList<E>();
		
		for (E object : result) {
			if (oneFilter.isMatch(object)) {
				filteredResult.add(object);
			}
		}
		
		return filteredResult;
	}
}
