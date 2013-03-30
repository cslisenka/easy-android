package by.easyandroid.framework;

import java.io.File;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.file.copy.CopyDirectoryTask;
import by.easyandroid.framework.task.file.copy.CopyFileTask;
import by.easyandroid.framework.task.file.create.CreateDirectoryTask;
import by.easyandroid.framework.task.file.create.CreateFileTask;

public class Copier {

	private static final String WORKING_DIR_PATH = "/";

	private TaskExecutor executor = new TaskExecutor();
	
	private String outputDirPath;
	
	public void setOutputDir(String outputDirPath) {
		this.outputDirPath = outputDirPath;
	}	
	
	public String getOutputDir() {
		return outputDirPath;
	}	
	
	// TODO javadocs!!!
	public void add(String from) {
		add(from, WORKING_DIR_PATH);
	}

	public void add(String from, String toDirectory) {
		File source = new File(from);
		String destPath = buildDestinationPath(toDirectory);
		
		if (source.isFile()) {
			executor.addTask(new CopyFileTask(source.getAbsolutePath(), destPath));
		} else if (source.isDirectory()) {
			executor.addTask(new CopyDirectoryTask(source.getAbsolutePath(), destPath));
		}
	}

	public void createFile(String pathToFile, String fileContent) {
		executor.addTask(new CreateFileTask(outputDirPath + File.separator + pathToFile, fileContent));
	}

	public void createFolder(String folderPath) {
		executor.addTask(new CreateDirectoryTask(outputDirPath + File.separator + folderPath));
	}	
	
	public void flush() throws TaskExecutionException {
//		cleanWorkingDirectory();
		executor.executeAll();
		executor.removeAllTasks();
	}

	// TODO fix unit-test
//	private void cleanWorkingDirectory() throws TaskExecutionException {
//		try {
//			// Create directory if not exisis
//			// TODO unit-test
//			File outputDir = new File(outputDirPath);
//			FileUtils.deleteDirectory(outputDir);
//			outputDir.mkdirs();
//		} catch (IOException e) {
//			throw new TaskExecutionException("Can not clean working directory before task executing", e);
//		}
//	}
	
	private String buildDestinationPath(String toDirectory) {
		if (!toDirectory.equals(WORKING_DIR_PATH)) {
			return outputDirPath + File.separator + toDirectory;
		}
		
		return outputDirPath;
	}	
}