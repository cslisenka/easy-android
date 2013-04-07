package by.easyandroid.service.datasource;

import java.io.File;

import by.easyandroid.service.exception.ApplicationServiceException;

/**
 * Stores compiled applications.
 * Returns link or path to stored application
 * 
 * @author kslisenko
 */
public interface IApplicationResultService {

	String uploadResultApk(String applicationInstanceObjId, File apkToUpload) throws ApplicationServiceException;
}