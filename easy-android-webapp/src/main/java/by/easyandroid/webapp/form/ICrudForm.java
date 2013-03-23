package by.easyandroid.webapp.form;

import java.util.List;

import javax.faces.event.ActionEvent;

import by.easyandroid.model.Identity;

public interface ICrudForm<T extends Identity> {

	void delete(ActionEvent event);
	
	void create(ActionEvent event);
	
	void edit(ActionEvent event);
	
	List<T> getAll();
}