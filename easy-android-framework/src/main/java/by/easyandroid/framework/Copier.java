package by.easyandroid.framework;

import java.io.File;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.file.CopyDirectoryTask;
import by.easyandroid.framework.task.file.CopyFileTask;
import by.easyandroid.framework.task.file.CreateDirectoryTask;
import by.easyandroid.framework.task.file.CreateFileTask;

public class Copier {

	private TaskExecutor executor = new TaskExecutor();
	
	private String outputDirPath;
	
	public void setOutputDir(String outputDirPath) {
		this.outputDirPath = outputDirPath;
	}	
	
	// TODO javadocs!!!
	public void add(String from) {
		add(from, "/");
	}

	public void add(String from, String toDirectory) {
		File f = new File(from);
		
		String to = outputDirPath;
		if (!toDirectory.equals("/")) {
			to += File.separator + toDirectory;
		}
		
		if (f.isFile()) {
			executor.addTask(new CopyFileTask(f.getAbsolutePath(), to));
		} else if (f.isDirectory()) {
			executor.addTask(new CopyDirectoryTask(f.getAbsolutePath(), to));
		}
	}

	public void createFile(String pathToFile, String fileContent) {
		executor.addTask(new CreateFileTask(outputDirPath + File.separator + pathToFile, fileContent));
	}

	public void createFolder(String folderPath) {
		executor.addTask(new CreateDirectoryTask(outputDirPath + File.separator + folderPath));
	}	
	
	public void flush() throws TaskExecutionException {
		executor.executeAll();
		executor.removeAllTasks();
	}
}