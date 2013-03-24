package by.easyandroid.template.conference.model;

/**
 * Category of report. Used for better searching reports by interests.
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

	@Override
	public String toString() {
		return name;
	}
}