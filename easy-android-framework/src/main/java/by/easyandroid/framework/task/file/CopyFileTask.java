package by.easyandroid.framework.task.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class CopyFileTask implements ITask {

	private String pathToFile;
	private String toDirectory;
	
	public CopyFileTask(String pathToFile, String toDirectory) {
		this.pathToFile = pathToFile;
		this.toDirectory = toDirectory;
	}

	public void execute() throws TaskExecutionException {
		try {
			File srcFile = new File(pathToFile);
			File dstFile = new File(new File(toDirectory), srcFile.getName());
			FileUtils.copyFile(srcFile, dstFile);
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}