package by.easyandroid.framework.task.file.create;

import java.io.File;

import by.easyandroid.framework.exception.TaskExecutionException;

public class CreateDirectoryTask extends AbstractCreateTask {

	public CreateDirectoryTask(String directoryPath) {
		super(directoryPath);
	}

	public void execute() throws TaskExecutionException {
		if (!new File(path).mkdirs()) {
			throw new TaskExecutionException("Error while create directory " + path);
		}
	}
}