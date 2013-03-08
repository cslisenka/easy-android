package by.easyandroid.model.conference;

import by.easyandroid.model.Identity;

/**
 * Category of report. Used for better seardhing reports by interests.
 * 
 * @author kslisenko
 */
public class Category extends Identity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}