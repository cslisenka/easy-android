package by.easyandroid.framework.task.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class CopyDirectoryTask implements ITask {

	private String fromDirectory;
	private String toDirectory;
	
	public CopyDirectoryTask(String fromDirectory, String toDirectory) {
		this.fromDirectory = fromDirectory;
		this.toDirectory = toDirectory;
	}

	public void execute() throws TaskExecutionException {
		try {
			FileUtils.copyDirectory(new File(fromDirectory), new File(toDirectory));
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}