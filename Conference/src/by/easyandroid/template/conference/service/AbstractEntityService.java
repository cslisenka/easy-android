package by.easyandroid.template.conference.service;

import java.util.List;

import by.easyandroid.template.conference.model.Identity;

public abstract class AbstractEntityService<E extends Identity> {

	public abstract List<E> getAll();
	
	public abstract E getById(long id);
}
