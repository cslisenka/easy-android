package by.easyandroid.service.datasource.impl;

import java.io.File;

import by.easyandroid.service.amazon.CloudStorageService;
import by.easyandroid.service.datasource.ITemplateSourceService;
import by.easyandroid.service.exception.ApplicationServiceException;

public class CloudStorageTemplateSourceService implements ITemplateSourceService {

	private CloudStorageService storageService;
	
	public CloudStorageTemplateSourceService(CloudStorageService storageService) {
		this.storageService = storageService;
	}
	
	// TODO sources should not be public available! Make required amazon settings
	// TODO create ant-script for uploading templates to aws s3 when deploying application in prod
	public void getApkTemplate(String templateId, File destDirectory) throws ApplicationServiceException {
		storageService.downloadFiles("templates/conference", destDirectory);
	}
}