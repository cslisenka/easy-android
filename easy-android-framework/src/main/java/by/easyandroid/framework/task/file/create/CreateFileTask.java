package by.easyandroid.framework.task.file.create;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;

public class CreateFileTask extends AbstractCreateTask {

	private String fileContent;
	
	public CreateFileTask(String pathToFile, String fileContent) {
		super(pathToFile);
		this.fileContent = fileContent;
	}

	public void execute() throws TaskExecutionException {
		File fileToCreate = new File(path);

		if (!fileToCreate.getParentFile().mkdirs()) {
			throw new TaskExecutionException("Can not create directories for file " + path);
		}
		
		try {
			FileUtils.writeStringToFile(fileToCreate, fileContent);
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}