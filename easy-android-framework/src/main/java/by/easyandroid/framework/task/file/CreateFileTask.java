package by.easyandroid.framework.task.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class CreateFileTask implements ITask {

	private String pathToFile;
	private String fileContent;
	
	public CreateFileTask(String pathToFile, String fileContent) {
		this.pathToFile = pathToFile;
		this.fileContent = fileContent;
	}

	public void execute() throws TaskExecutionException {
		File fileToCreate = new File(pathToFile);

		if (!fileToCreate.getParentFile().mkdirs()) {
			throw new TaskExecutionException("Can not create directories for file " + pathToFile);
		}
		
		try {
			FileUtils.writeStringToFile(fileToCreate, fileContent);
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}