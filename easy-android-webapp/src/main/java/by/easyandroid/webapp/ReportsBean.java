package by.easyandroid.webapp;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;

@ManagedBean
@SessionScoped
public class ReportsBean {

	private List<Report> reports = new ArrayList<Report>();
	private List<Reporter> reporters = new ArrayList<Reporter>();
	private List<Section> sections = new ArrayList<Section>();
	
	private boolean addReportsForm;

	public ReportsBean() {
		// Init reporters
		Reporter rp1 = new Reporter();
		rp1.setId("1");
		rp1.setName("mr. Dave Nilson");
		rp1.setPosition("Software architect in Amazon");
		reporters.add(rp1);

		Reporter rp2 = new Reporter();
		rp2.setId("2");
		rp2.setName("mr. Konstantin Slisenko");
		rp2.setPosition("Senior software engineer in JazzTeam");
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
		
		// Categories
		Category c1 = new Category();
		c1.setId("1");
		c1.setName("Short talk");
		
		Category c2 = new Category();
		c2.setId("2");
		c2.setName("Report/master class");		
		
		// Init reports
		Report r1 = new Report();
		r1.setId("1");
		r1.setTitle("Cloud architectures for enterprice");
		r1.setDescription("In this short talk mr. Dave Nilson describes about architectures concepts, related to enterprice applications world.");
		r1.setReporter(rp1);
		r1.setSection(s1);
		r1.setTime(new GregorianCalendar().getTime());
		r1.setCategory(c2);
		reports.add(r1);

		Report r2 = new Report();
		r2.setId("2");
		r2.setTitle("Android development for beginners");
		r2.setDescription("How to start develop for android platform? Which tools do we need? I'm going to answer this and all another questions in my report.");
		r2.setReporter(rp1);
		r2.setSection(s2);
		r2.setTime(new GregorianCalendar().getTime());
		r2.setCategory(c2);
		reports.add(r2);

		Report r3 = new Report();
		r3.setId("3");
		r3.setTitle("EasyAndroid startup");
		r3.setDescription("Create powerful business-class android applications without programming skills. It's possible!");
		r3.setReporter(rp2);
		r3.setSection(s1);
		r3.setTime(new GregorianCalendar().getTime());
		r3.setCategory(c2);
		reports.add(r3);
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