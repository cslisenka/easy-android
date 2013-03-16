package by.easyandroid.database.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.database.service.conference.CategoryService;
import by.easyandroid.database.service.conference.ReportService;
import by.easyandroid.database.service.conference.ReporterService;
import by.easyandroid.database.service.conference.SectionService;
import by.easyandroid.database.service.exception.DatabaseServiceException;
import by.easyandroid.database.util.DatabaseUtil;
import by.easyandroid.database.util.TestObjectCreator;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.ApplicationTemplate;
import by.easyandroid.model.conference.Category;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.ConferenceInformation;
import by.easyandroid.model.conference.Report;
import by.easyandroid.model.conference.Reporter;
import by.easyandroid.model.conference.Section;

public class TestApplicationInstanceService extends AbstractGenericServiceTest<ApplicationInstanceService, ApplicationInstance> {

	@Test
	public void testFullCopy() throws DatabaseServiceException {
		// TODO to set up
		TemplateService ts = new TemplateService(mongoOperation);
		SectionService ss = new SectionService(mongoOperation);
		CategoryService cs = new CategoryService(mongoOperation);
		ReportService rs = new ReportService(mongoOperation);
		ReporterService rrs = new ReporterService(mongoOperation);
		
		service.setCategoryService(cs);
		service.setSectionService(ss);
		service.setReportService(rs);
		service.setReporterService(rrs);
		
		// Create application instance
		ApplicationTemplate template = TestObjectCreator.fakeTemplate("test template");
		ts.add(template);

		// Save model content: reports, reporters, sections, categories
		// Common reporter object for two reports
		Reporter rep = TestObjectCreator.fakeReporter("reporter");
		rrs.add(rep);

		// Separate sections and categories objects for two reports
		Section s1 = TestObjectCreator.fakeSection("section1");
		ss.add(s1);
		Category c1 = TestObjectCreator.fakeCategory("cat1");
		cs.add(c1);
		
		Report r1 = TestObjectCreator.fakeReport("report1");
		r1.setSection(s1);
		r1.setCategory(c1);
		r1.setReporter(rep);
		rs.add(r1);
		
		Section s2 = TestObjectCreator.fakeSection("section2");
		ss.add(s2);
		Category c2 = TestObjectCreator.fakeCategory("cat2");
		cs.add(c2);
		
		Report r2 = TestObjectCreator.fakeReport("report2");		
		r2.setSection(s2);
		r2.setCategory(c2);
		r2.setReporter(rep);
		rs.add(r2);
		
		// Save instance and model
		ConferenceApplicationModel model = TestObjectCreator.fakeModel("some conference");
		ApplicationInstance instance = TestObjectCreator.fakeInstance("testname");
		instance.setModel(model);
		instance.setTemplate(template);
		
		model.getReports().add(r1);
		model.getReports().add(r2);
		model.getReporters().add(rep);
		model.getSections().add(s1);
		model.getSections().add(s2);
		model.getCategories().add(c1);
		model.getCategories().add(c2);
		
		service.add(instance);
		
		Assert.assertEquals(1, rrs.getAll().size());

		ApplicationInstance copied = service.fullCopy(instance.getId());
		
		// Test all information copied
		Assert.assertEquals(2, service.getAll().size()); // 2 instances
		Assert.assertEquals(1, ts.getAll().size()); // 1 template
		Assert.assertEquals(4, ss.getAll().size()); // 4 sections
		Assert.assertEquals(4, cs.getAll().size()); // 4 categories
		Assert.assertEquals(2, rrs.getAll().size()); // 2 reporters
		Assert.assertEquals(4, rs.getAll().size()); // 4 reports
		
		Set<String> oldModelObjectIds = new HashSet<String>();
		oldModelObjectIds.addAll(DatabaseUtil.getIds(model.getReports()));
		oldModelObjectIds.addAll(DatabaseUtil.getIds(model.getReporters()));
		oldModelObjectIds.addAll(DatabaseUtil.getIds(model.getSections()));
		oldModelObjectIds.addAll(DatabaseUtil.getIds(model.getCategories()));
		
		ConferenceApplicationModel copiedModel = copied.getModel();
		Set<String> newModelObjectIds = new HashSet<String>();
		newModelObjectIds.addAll(DatabaseUtil.getIds(copiedModel.getReports()));
		newModelObjectIds.addAll(DatabaseUtil.getIds(copiedModel.getReporters()));
		newModelObjectIds.addAll(DatabaseUtil.getIds(copiedModel.getSections()));
		newModelObjectIds.addAll(DatabaseUtil.getIds(copiedModel.getCategories()));
		
		for (String newModelId : newModelObjectIds) {
			Assert.assertFalse(oldModelObjectIds.contains(newModelId));
		}
	}
	
	@Override
	protected void assertTestEntity(ApplicationInstance inst) {
		Assert.assertEquals("test", inst.getName());
		Assert.assertEquals(new Date(2012, 10, 30, 10, 15), inst.getCreationDate());
		Assert.assertTrue(inst.isLastApkUpToDate());
		
		// Check model
		Assert.assertNotNull(inst.getModel());
		Assert.assertNotNull(inst.getModel().getInformation());
		
		// Check conference information
		ConferenceInformation inf = inst.getModel().getInformation();
		Assert.assertEquals("title", inf.getTitle());
		Assert.assertEquals("about", inf.getAbout());
		Assert.assertEquals("url", inf.getWebsiteUrl());
	}

	@Override
	protected ApplicationInstance createTestEntity() {
		ApplicationInstance inst = new ApplicationInstance();
		inst.setCreationDate(new Date(2012, 10, 30, 10, 15));
		inst.setName("test");
		inst.setLastApkUpToDate(true);
		
		// Create model for instance
		ConferenceApplicationModel model = new ConferenceApplicationModel();
		ConferenceInformation inf = new ConferenceInformation();
		inf.setTitle("title");
		inf.setAbout("about");
		inf.setWebsiteUrl("url");
		model.setInformation(inf);
		inst.setModel(model);
		
		return inst;
	}

	@Override
	protected ApplicationInstanceService createService(MongoOperations mongo) {
		return new ApplicationInstanceService(mongo);
	}
}