package by.easyandroid.worker.webapp.service;

import javax.jws.WebService;

import by.easyandroid.service.compilation.ApplicationBuildService;
import by.easyandroid.service.exception.ApplicationServiceException;

@WebService(endpointInterface = "by.easyandroid.worker.webapp.service.AndroidAppBuildWS")
public class AndroidAppBuildWSImpl implements AndroidAppBuildWS {

	private ApplicationBuildService buildService;
	
	@Override
	public void buildAndroidApplication(String applicationObjId) throws ApplicationServiceException {
		buildService.build(applicationObjId);
	}

	public ApplicationBuildService getBuildService() {
		return buildService;
	}

	public void setBuildService(ApplicationBuildService buildService) {
		this.buildService = buildService;
	}
}