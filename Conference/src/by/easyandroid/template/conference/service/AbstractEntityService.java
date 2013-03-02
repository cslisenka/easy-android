package by.easyandroid.template.conference.service;

import java.util.List;

import android.content.Context;
import by.easyandroid.template.conference.model.Identity;

public abstract class AbstractEntityService<E extends Identity> {

	public static final String DATA_XML = "conference_data.xml";
	
	protected Context context;

	public AbstractEntityService(Context context) {
		this.context = context;
	}
	
	public abstract List<E> getAll();
	
	public abstract E getById(long id);
}
