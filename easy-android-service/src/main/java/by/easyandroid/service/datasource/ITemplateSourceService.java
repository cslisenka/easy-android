package by.easyandroid.service.datasource;

import java.io.File;

import by.easyandroid.service.exception.ApplicationServiceException;


/**
 * Provides application templates.
 * Service may have different implementations: local file system or amazon s3 storage.
 * Result template must b
 * 
 * @author kslisenko
 */
public interface ITemplateSourceService {

	/**
	 * Gets template by database object id and copies it into specified directory
	 * @param templateId
	 * @param destDirectory
	 */
	void getApkTemplate(String templateId, File destDirectory) throws ApplicationServiceException;
}