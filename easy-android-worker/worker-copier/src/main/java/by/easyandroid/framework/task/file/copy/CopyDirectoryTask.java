package by.easyandroid.framework.task.file.copy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import by.easyandroid.framework.exception.TaskExecutionException;

public class CopyDirectoryTask extends AbstractCopyTask {

	public CopyDirectoryTask(String fromDirectory, String toDirectory) {
		super(fromDirectory, toDirectory);
	}

	public void execute() throws TaskExecutionException {
		try {
			FileUtils.copyDirectory(new File(from), new File(to));
		} catch (IOException e) {
			throw new TaskExecutionException(e);
		}
	}
}