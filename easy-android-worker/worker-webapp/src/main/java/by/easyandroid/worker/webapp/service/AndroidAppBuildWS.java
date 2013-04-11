package by.easyandroid.worker.webapp.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import by.easyandroid.service.exception.ApplicationServiceException;

/**
 * Web service for building android applications
 * @author kslisenko
 *
 */
@WebService
public interface AndroidAppBuildWS {

	@WebMethod
	void buildAndroidApplication(String applicationObjId) throws ApplicationServiceException;
}