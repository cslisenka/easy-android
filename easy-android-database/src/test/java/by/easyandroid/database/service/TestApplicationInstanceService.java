package by.easyandroid.database.service;

import java.util.Date;

import junit.framework.Assert;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.model.conference.ConferenceInformation;

public class TestApplicationInstanceService extends AbstractGenericServiceTest<ApplicationInstanceService, ApplicationInstance> {

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