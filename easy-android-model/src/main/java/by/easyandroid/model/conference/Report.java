package by.easyandroid.model.conference;

import java.util.Date;

import by.easyandroid.model.Identity;

public class Report extends Identity {

	private String title;
	
	private String description;
	
	private Date time;
	
	private Section section;
	
	private Category category;
	
	private Reporter reporter;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desctiption) {
		this.description = desctiption;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}
}