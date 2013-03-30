package by.easyandroid.framework.task.file.copy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;

public class CopyFileTask extends AbstractCopyTask {

	public CopyFileTask(String pathToFile, String toDirectory) {
		super(pathToFile, toDirectory);
	}

	public void execute() throws TaskExecutionException {
		try {
			File srcFile = new File(from);
			File dstFile = new File(new File(to), srcFile.getName());
			FileUtils.copyFile(srcFile, dstFile);
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}