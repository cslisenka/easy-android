package by.easyandroid.service.compilation.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

// TODO unit-test
/**
 * Represents directory where compilation process runs.
 * Wraps core java classes with additional specific logic.
 * @author kslisenko
 */
public class WorkingDirectory {

	private File directory;
	
	public WorkingDirectory() throws IOException {
		directory = createTempDirectory();
//			throw new ApplicationServiceException("Can not create working directory for apk building process");
	}

	public void remove() throws IOException {
		FileUtils.deleteDirectory(directory);
//			throw new ApplicationServiceException("Can not delete working directory for apk building process");
	}
	
	public File getDirectory() {
		return directory;
	}
	
	public static File createTempDirectory() throws IOException {
		File tempFile = File.createTempFile("temp", "");
		tempFile.delete();
		tempFile.mkdirs();
		return tempFile;
	}	
}