package by.easyandroid.service.datasource.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.service.datasource.ITemplateSourceService;
import by.easyandroid.service.exception.ApplicationServiceException;

/**
 * Provides application templates from local directory
 * @author kslisenko
 */
//TODO unit-test
public class FileSystemTemplateSourceService implements ITemplateSourceService {

	/**
	 * Directory with application templates
	 */
	private File templatesDir;

	public FileSystemTemplateSourceService(String templatesDir) {
		this.templatesDir = new File(templatesDir);
	}
	
	public void getApkTemplate(String templateId, File destDirectory) throws ApplicationServiceException {
		// TODO now we have only one template and first argument templateId is ignored
		
		File dirWithTemplate = new File(destDirectory, "conference");
		if (!dirWithTemplate.exists() || !dirWithTemplate.isDirectory()) {
			throw new ApplicationServiceException("Directory with template '" + dirWithTemplate.getAbsolutePath() + "' not exists");
		}
		
		try {
			FileUtils.copyDirectory(dirWithTemplate, destDirectory);
		} catch (IOException e) {
			throw new ApplicationServiceException("can not copy template", e);
		}
	}

	public File getTemplatesDir() {
		return templatesDir;
	}
}