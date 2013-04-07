package by.easyandroid.service.datasource.impl;

import java.io.File;
import java.util.GregorianCalendar;

import by.easyandroid.service.amazon.CloudStorageService;
import by.easyandroid.service.datasource.IApplicationResultService;
import by.easyandroid.service.exception.ApplicationServiceException;

// TODO unit-test
// TODO when user deletes application instance, we need to delete it builds
public class CloudStorageApplicationResultService implements IApplicationResultService {

	private CloudStorageService storageService;
	
	public CloudStorageApplicationResultService(CloudStorageService storageService) {
		this.storageService = storageService;
	}
	
	public String uploadResultApk(String applicationInstanceObjId, File apkToUpload) throws ApplicationServiceException {
		
		storageService.clearFolder(applicationInstanceObjId);
		return storageService.uploadPublicFile(apkToUpload, applicationInstanceObjId, generateApkFileName());
	}
	
	protected String generateApkFileName() {
		return String.format("conference%d.apk", new GregorianCalendar().getTimeInMillis());
	}	
}