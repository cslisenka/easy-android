package by.easyandroid.service.compilation;

import java.io.File;
import java.util.List;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.framework.Copier;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.service.exception.ApplicationServiceException;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.conference.ConferenceAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;

/**
 * Service for android projects building and compilation
 * @author kslisenko
 */
public class CompilationService {

	private ApplicationInstanceService applicationService;
	
	/**
	 * TODO in future this method would be divided to several operations:
	 * 1. putting message to queue (task for compilation)
	 * 2. retrieving message from queue
	 * 3. calling web-service to start compilation on separate webapp
	 * 4. start compilation
	 * 
	 * Starts compilation of specified application.
	 * Finds application by id.
	 * 
	 * @param applicationId
	 * @throws ApplicationServiceException
	 */
	public void doCompilation(String applicationId) throws ApplicationServiceException {
		// 1. get application from database
		ApplicationInstance application = applicationService.get(applicationId);
		if (application == null) {
			throw new ApplicationServiceException("Can not find application with id '" + applicationId + "'");
		}
		
		build(application.getModel());
	}

	protected void build(ConferenceApplicationModel model) throws ApplicationServiceException {
		// 2. Create adapter
		ConferenceAdapter adapter = new ConferenceAdapter(model);
		
		// TODO temporary path to template in WEB-INF
		String pathToSources = new File("").getAbsolutePath() + "/../Conference";
		// TODO this is temporary solution, we need to put project sources into target folder during build
		adapter.setSourcesPath(pathToSources);		
		
		try {
			List<FileEntry> convertedFiles = adapter.convert();
			Copier copier = new Copier();
			// TODO now we copy to target, in future we need to copy to special directory on server
			copier.setOutputDir(new File("").getAbsolutePath() + "/target/buildResults");
			copier.add(new File("").getAbsolutePath() + "/../Conference");
			for (FileEntry entry : convertedFiles) {
				copier.createFile(entry.getPathInProject(), entry.getFileContent());
			}
			
			copier.flush();
			
			AndroidApkBuilder builder = new AndroidApkBuilder();
			builder.build(copier.getOutputDir());	
			
			// TODO return link to compiled apk
		} catch (TemplateAdapterException e) {
			throw new ApplicationServiceException(e);
		} catch (TaskExecutionException e) {
			throw new ApplicationServiceException(e);
		}
	}
	
	public ApplicationInstanceService getApplicationService() {
		return applicationService;
	}

	public void setApplicationService(ApplicationInstanceService applicationService) {
		this.applicationService = applicationService;
	}
}