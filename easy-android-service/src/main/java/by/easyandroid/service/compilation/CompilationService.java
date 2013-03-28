package by.easyandroid.service.compilation;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;

import by.easyandroid.database.service.ApplicationInstanceService;
import by.easyandroid.framework.Copier;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.model.ApplicationInstance;
import by.easyandroid.model.conference.ConferenceApplicationModel;
import by.easyandroid.service.exception.ApplicationServiceException;
import by.easyandroid.service.util.FileUtil;
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
	public void doCompilation(String applicationId, String pathToWebRoot) throws ApplicationServiceException {
		// 1. get application from database
		ApplicationInstance application = applicationService.get(applicationId);
		if (application == null) {
			throw new ApplicationServiceException("Can not find application with id '" + applicationId + "'");
		}
		
		String workingDirectoryPath = null;
		try {
			workingDirectoryPath = FileUtil.createTempDirectory().getAbsolutePath();
		} catch (IOException e) {
			throw new ApplicationServiceException("Can not create temporary folder", e);
		}
		
		String templatePath = pathToWebRoot + "/androidTemplate/Conference";
		runBuildProcess(application.getModel(), workingDirectoryPath, templatePath);	
		
		File srcFile = new File(workingDirectoryPath + 
				File.separator + "bin" + File.separator + "MyAndroidApp-debug.apk");
		File dstFile = new File(pathToWebRoot + File.separator + "androidApplication" + 
				File.separator + "conference" + new GregorianCalendar().getTimeInMillis() + ".apk");
		// Copy result apk from temp directory to web directory
		try {
			FileUtils.copyFile(srcFile, dstFile);
		} catch (IOException e) {
			throw new ApplicationServiceException("Can not copy result application to web directory", e);
		}
	}

	/**
	 * 1. Copies template to working directory.
	 * 2. Uses adapter to convert model to project source files, copies them into working directory.
	 * 3. Executes build script in working directory
	 * @param model
	 * @throws ApplicationServiceException
	 */
	protected void runBuildProcess(ConferenceApplicationModel model, String workingDirectoryPath, String templatePath) throws ApplicationServiceException {
		// 2. Create adapter
		ConferenceAdapter adapter = new ConferenceAdapter(model);
		adapter.setSourcesPath(templatePath);		
		
		Copier copier = new Copier();
		copier.setOutputDir(workingDirectoryPath);
		copier.add(templatePath);		
		
		try {
			List<FileEntry> convertedFiles = adapter.convert();
			
			for (FileEntry entry : convertedFiles) {
				copier.createFile(entry.getPathInProject(), entry.getFileContent());
			}
			
			copier.flush();
			
			AndroidApkBuilder builder = new AndroidApkBuilder();
			builder.build(copier.getOutputDir());	
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