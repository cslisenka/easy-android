package by.easyandroid.database.service;

import junit.framework.Assert;

import org.springframework.data.mongodb.core.MongoOperations;

import by.easyandroid.model.ApplicationTemplate;

public class TestTemplateService extends AbstractGenericServiceTest<TemplateService, ApplicationTemplate> {

	@Override
	protected TemplateService createService(MongoOperations mongo) {
		return new TemplateService(mongo);
	}

	@Override
	protected ApplicationTemplate createTestEntity() {
		ApplicationTemplate template = new ApplicationTemplate();
		template.setTitle("title");
		template.setWebUIPartUrl("url");
		template.setTemplateSourceUrl("source url");
		template.setDescription("description");
		template.setSampleApkUrl("saved apk url");
		template.setConverterClassName("class");
		return template;
	}

	@Override
	protected void assertTestEntity(ApplicationTemplate template) {
		Assert.assertEquals("title", template.getTitle());
		Assert.assertEquals("url", template.getWebUIPartUrl());
		Assert.assertEquals("source url", template.getTemplateSourceUrl());
		Assert.assertEquals("description", template.getDescription());
		Assert.assertEquals("saved apk url", template.getSampleApkUrl());
		Assert.assertEquals("class", template.getConverterClassName());
	}
}