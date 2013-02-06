package org.jazzteam.easyandroid.webapp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.jazzteam.easyandroid.webapp.model.Report;
import org.jazzteam.easyandroid.webapp.model.Reporter;
import org.jazzteam.easyandroid.webapp.model.Section;

@ManagedBean
@SessionScoped
public class ReportsBean {

	private List<Report> reports = new ArrayList<Report>();
	private List<Reporter> reporters = new ArrayList<Reporter>();
	private List<Section> sections = new ArrayList<Section>();
	
	private boolean addReportsForm;

	public ReportsBean() {
		// Init reports
		Report r1 = new Report();
		r1.setId("1");
		r1.setName("Cloud architectures for enterprice");
		r1.setDescription("In this short talk mr. Dave Nilson describes about architectures concepts, related to enterprice applications world.");
		r1.setReporter("mr. Dave Nilson");
		r1.setSection("A");
		r1.setTime("12:00");
		r1.setType("Short talk");
		reports.add(r1);

		Report r2 = new Report();
		r2.setId("2");
		r2.setName("Android development for beginners");
		r2.setDescription("How to start develop for android platform? Which tools do we need? I'm going to answer this and all another questions in my report.");
		r2.setReporter("mr. Konstantin Slisenko");
		r2.setSection("B");
		r2.setTime("12:00");
		r2.setType("Report/master class");
		reports.add(r2);

		Report r3 = new Report();
		r3.setId("3");
		r3.setName("EasyAndroid startup");
		r3.setDescription("Create powerful business-class android applications without programming skills. It's possible!");
		r3.setReporter("mr. Konstantin Slisenko");
		r3.setSection("A");
		r3.setTime("12:30");
		r3.setType("Report");
		reports.add(r3);

		// Init reporters
		Reporter rp1 = new Reporter();
		rp1.setId("1");
		rp1.setName("mr. Dave Nilson");
		rp1.setPosition("Software architect in Amazon");
		rp1.setCity("Dallas");
		reporters.add(rp1);

		Reporter rp2 = new Reporter();
		rp2.setId("2");
		rp2.setName("mr. Konstantin Slisenko");
		rp2.setPosition("Senior software engineer in JazzTeam");
		rp2.setCity("Minsk");
		reporters.add(rp2);
		
		// Sections
		Section s1 = new Section();
		s1.setId("1");
		s1.setName("A");
		sections.add(s1);

		Section s2 = new Section();
		s2.setId("2");
		s2.setName("B");
		sections.add(s2);
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<Reporter> getReporters() {
		return reporters;
	}

	public void setReporters(List<Reporter> reporters) {
		this.reporters = reporters;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public boolean isAddReportsForm() {
		return addReportsForm;
	}

	public void setAddReportsForm(boolean addReportsForm) {
		this.addReportsForm = addReportsForm;
	}
	
	public void closeAddReportsForm() {
		addReportsForm = false;
	}
	
	public void openAddReportsForm() {
		addReportsForm = true;
	}
	
	public List<SelectItem> getSectionsSelectItems() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		
		for (Section s : sections) {
			items.add(new SelectItem(s, s.getName()));
		}
		
		return items;
	}
}