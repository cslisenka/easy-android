package by.easyandroid.service.compilation;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.service.exception.ApplicationServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-context.xml")
public class TestCompilationService {

	@Autowired
	private CompilationService compilationService;
	
	private ConferenceApplicationModel model;

	@Test(expected = ApplicationServiceException.class)
	public void testWrongApplication() throws ApplicationServiceException {
		compilationService.doCompilation("wrongid", "");
	}

	@Test
	public void testApplicationBuilding() throws ApplicationServiceException {
		createFakeModel();
		// TODO copy android project sources to target folder during build
		String workingDirectoryPath = new File("").getAbsolutePath() + "/target/buildResults";
		String templatePath = new File("").getAbsolutePath() + "/../Conference";		
		compilationService.runBuildProcess(model, workingDirectoryPath, templatePath);
		// TODO check that compilation were success		
	}

	private void createFakeModel() {
		model = new ConferenceApplicationModel();
		model.getInformation().setAbout("Some text about conference");
		model.getInformation().setWebsiteUrl("http://test.conference.com");
		model.getInformation().setTitle("My test conference");

		// Create fake sections
		Section s1 = addSection("1", "section 1");
		Section s2 = addSection("2", "section 2");

		// Create fake categories
		Category c1 = addCategory("1", "category 1");
		Category c2 = addCategory("2", "category 2");

		Reporter r1 = addReporter("1", "reporter 1");
		Reporter r2 = addReporter("2", "reporter 2");

		addReport("1", "report 1", s1, c1, r1);
		addReport("2", "report 2", s2, c2, r2);
	}

	private void addReport(String id, String name, Section s1, Category c1, Reporter r1) {
		Report r = new Report();
		r.setId(id);
		r.setTitle(name);
		r.setReporter(r1);
		r.setSection(s1);
		r.setCategory(c1);
		r.setDescription("description " + name);
		r.setTime(new Date(2013, 10, 10, 5, 30));
		model.getReports().add(r);
	}

	private Reporter addReporter(String id, String name) {
		Reporter r = new Reporter();
		r.setId(id);
		r.setName(name);
		r.setDescription("description " + name);
		r.setEmail("email " + name);
		r.setPosition("position " + name);
		r.setCompany("company " + name);
		model.getReporters().add(r);
		return r;
	}

	private Category addCategory(String id, String name) {
		Category c = new Category();
		c.setName(name);
		c.setId(id);
		model.getCategories().add(c);
		return c;
	}

	private Section addSection(String id, String name) {
		Section s = new Section();
		s.setName(name);
		s.setId(id);
		model.getSections().add(s);
		return s;
	}
}