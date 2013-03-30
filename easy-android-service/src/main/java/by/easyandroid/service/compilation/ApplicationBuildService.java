package by.easyandroid.service.compilation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.framework.Copier;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.service.compilation.util.AndroidApkBuilder;
import by.easyandroid.service.compilation.util.WorkingDirectory;
import by.easyandroid.service.datasource.IApplicationResultService;
import by.easyandroid.service.datasource.ITemplateSourceService;
import by.easyandroid.service.exception.ApplicationServiceException;
import by.easyandroid.template.adapter.FileEntry;
import by.easyandroid.template.adapter.conference.ConferenceAdapter;
import by.easyandroid.template.adapter.exception.TemplateAdapterException;

/**
 * Service for android projects building and compilation
 * @author kslisenko
 */
public class ApplicationBuildService {

	private ApplicationInstanceService applicationService;
	private IApplicationResultService applicationResultService;
	private ITemplateSourceService templateSourceService;
	
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
	public void build(String applicationId) throws ApplicationServiceException {
		// 1. get application from database
		ApplicationInstance application = applicationService.get(applicationId);
		if (application == null) {
			throw new ApplicationServiceException("Can not find application with id '" + applicationId + "'");
		}
		
		buildApplication(application);
	}

	protected void buildApplication(ApplicationInstance application) throws ApplicationServiceException {
		WorkingDirectory workingDirectory = null;
		try {
			workingDirectory = new WorkingDirectory();
			buildApplicationInWorkingDirectory(application, workingDirectory.getDirectory());
		} catch (IOException e) {
			throw new ApplicationServiceException("Error with working directory", e);
		} finally {
			try {
				if (workingDirectory != null) {
					workingDirectory.remove();
				}
			} catch (IOException e) {
				throw new ApplicationServiceException("Can not remove working directory", e);
			}
		}
	}	
	
	/**
	 * TODO update this javadoc
	 * 1. Copies template to working directory.
	 * 2. Uses adapter to convert model to project source files, copies them into working directory.
	 * 3. Executes build script in working directory
	 * @param model
	 * @throws ApplicationServiceException
	 */
	protected void buildApplicationInWorkingDirectory(ApplicationInstance application, File workingDirectory) throws ApplicationServiceException {
		templateSourceService.getApkTemplate(application.getTemplate().getId(), workingDirectory);
		buildApplicationFromModel(application.getModel(), workingDirectory);
		processResult(application, new File(workingDirectory, "bin" + File.separator + "MyAndroidApp-debug.apk"));
	}

	/**
	 * Builds application in working directory by model
	 * @param model
	 * @param workingDirectory
	 * @throws ApplicationServiceException
	 */
	protected void buildApplicationFromModel(ConferenceApplicationModel model, File workingDirectory) throws ApplicationServiceException {
		// 2. Create adapter
		ConferenceAdapter adapter = new ConferenceAdapter(model);
		adapter.setSourcesPath(workingDirectory.getAbsolutePath());		
		
		// 3. Create copier
		Copier copier = new Copier();
		copier.setOutputDir(workingDirectory.getAbsolutePath());
		
		try {
			// 4. Convert files
			List<FileEntry> convertedFiles = adapter.convert();
			
			for (FileEntry entry : convertedFiles) {
				copier.createFile(entry.getPathInProject(), entry.getFileContent());
			}
			
			copier.flush();
			
			// 5. Build apk
			AndroidApkBuilder builder = new AndroidApkBuilder();
			builder.build(workingDirectory.getAbsolutePath());	
		} catch (TemplateAdapterException e) {
			throw new ApplicationServiceException(e);
		} catch (TaskExecutionException e) {
			throw new ApplicationServiceException(e);
		}		
	}
	
	/**
	 * Copies result apk to web available download directory and changes last upload apk link
	 * 
	 * @param application
	 * @param resultApk
	 * @throws ApplicationServiceException
	 */
	protected void processResult(ApplicationInstance application, File resultApk) throws ApplicationServiceException {
		String appDownloadLink = applicationResultService.uploadResultApk(resultApk);
		application.setLastCreatedApkUrl(appDownloadLink);
		applicationService.save(application);
	}

	public ApplicationInstanceService getApplicationService() {
		return applicationService;
	}

	public void setApplicationService(ApplicationInstanceService applicationService) {
		this.applicationService = applicationService;
	}

	public IApplicationResultService getApplicationResultService() {
		return applicationResultService;
	}

	public void setApplicationResultService(
			IApplicationResultService applicationResultService) {
		this.applicationResultService = applicationResultService;
	}

	public ITemplateSourceService getTemplateSourceService() {
		return templateSourceService;
	}

	public void setTemplateSourceService(
			ITemplateSourceService templateSourceService) {
		this.templateSourceService = templateSourceService;
	}
}