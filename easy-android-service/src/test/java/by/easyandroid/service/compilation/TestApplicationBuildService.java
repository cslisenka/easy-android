package by.easyandroid.service.compilation;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;
import by.easyandroid.service.compilation.util.WorkingDirectory;
import by.easyandroid.service.datasource.impl.FileSystemApplicationResultService;
import by.easyandroid.service.datasource.impl.FileSystemTemplateSourceService;
import by.easyandroid.service.exception.ApplicationServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-context.xml")
public class TestApplicationBuildService {

	@Autowired
	private ApplicationBuildService compilationService;
	
//	@Autowired
	private FileSystemApplicationResultService resultService;
	
//	@Autowired
	private FileSystemTemplateSourceService sourceService;
	
	private ConferenceApplicationModel model;
	private ApplicationInstance instance;

	private WorkingDirectory wrkDirectory;
	
	@Before
	public void setUp() throws IOException {
		wrkDirectory = new WorkingDirectory();
		createFakeApplication();
		
		File testTemplatePath = new File(new File("").getAbsoluteFile(), "src/test/resources/testApplicationSources/app-conference");
		FileUtils.copyDirectory(testTemplatePath, wrkDirectory.getDirectory());

		System.out.println("Working directory at " + wrkDirectory.getDirectory().getAbsolutePath());
	}
	
	@Test(expected = ApplicationServiceException.class)
	public void testWrongApplication() throws ApplicationServiceException {
		compilationService.build("wrongid");
	}

	@Test
	public void testBuildApplicationFromModel() throws ApplicationServiceException, IOException {
		compilationService.buildApplicationFromModel(model, wrkDirectory.getDirectory());
		Assert.assertTrue(new File(wrkDirectory.getDirectory(), "bin/MyAndroidApp-debug.apk").exists());
	}
	
	/**
	 * Test whole build process with datasource services
	 * @throws ApplicationServiceException 
	 * @throws IOException 
	 */
	@Test
	public void testBuildApplication() throws ApplicationServiceException, IOException {
		sourceService.setTemplatesDir(new File(new File("").getAbsoluteFile(), "src/test/resources/testApplicationSources"));
		resultService.setApkDirectory(new File(new File("").getAbsoluteFile(), "target/apk"));
		compilationService.buildApplication(instance);
		Assert.assertTrue(new File(new File("").getAbsoluteFile(), "target/apk").listFiles().length > 0);
		
		String apkFileName = new File(new File("").getAbsoluteFile(), "target/apk").listFiles()[0].getName();
		Assert.assertTrue(apkFileName.contains("conference"));
		Assert.assertEquals(resultService.getWepAccessDirectoryPath() + "/" + apkFileName, instance.getLastCreatedApkUrl());
	}
	
	@After
	public void tearDown() throws IOException {
		wrkDirectory.remove();
		FileUtils.deleteDirectory(new File(new File("").getAbsoluteFile(), "target/apk"));
	}

	private void createFakeApplication() {
		createFakeModel();
		instance = new ApplicationInstance();
		instance.setCreationDate(new Date());
		instance.setModel(model);
		instance.setName("test app");
		instance.setTemplate(new ApplicationTemplate());
	}
	
	private void createFakeModel() {
		model = new ConferenceApplicationModel();
		model.getInformation().setAbout("Some text about conference");
		model.getInformation().setWebsiteUrl("http://test.conference.com");
		model.getInformation().setTitle("My test conference");

		// Create fake sections
		Section s1 = addSection("sec1", "section 1");
		Section s2 = addSection("sec2", "section 2");

		// Create fake categories
		Category c1 = addCategory("cat1", "category 1");
		Category c2 = addCategory("cat2", "category 2");

		Reporter r1 = addReporter("rep1", "reporter 1");
		Reporter r2 = addReporter("rep2", "reporter 2");

		addReport("report1", "report 1", s1, c1, r1);
		addReport("report2", "report 2", s2, c2, r2);
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