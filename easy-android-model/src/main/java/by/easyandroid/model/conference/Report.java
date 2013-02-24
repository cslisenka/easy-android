package by.easyandroid.model.conference;

import java.util.Date;

import by.easyandroid.model.Identity;

public class Report extends Identity {

	private String title;
	
	private String desctiption;
	
	private Date time;
	
	private Section section;
	
	private Cathegory category;
	
	private Reporter reporter;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesctiption() {
		return desctiption;
	}

	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
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

	public Cathegory getCategory() {
		return category;
	}

	public void setCategory(Cathegory category) {
		this.category = category;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporter) {
		this.reporter = reporter;
	}
}