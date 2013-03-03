package by.easyandroid.framework.task.file;

import java.io.File;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class CreateDirectoryTask implements ITask {

	private String directoryPath;
	
	public CreateDirectoryTask(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void execute() throws TaskExecutionException {
		if (!new File(directoryPath).mkdirs()) {
			throw new TaskExecutionException("Error while create directory " + directoryPath);
		}
	}
}