package by.easyandroid.service.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	public static File createTempDirectory() throws IOException {
		File tempFile = File.createTempFile("temp", "");
		tempFile.delete();
		tempFile.mkdirs();
		return tempFile;
	}
}