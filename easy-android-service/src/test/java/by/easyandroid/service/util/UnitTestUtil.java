package by.easyandroid.service.util;

import java.io.File;

public class UnitTestUtil {

	public static File getFolderInTarget(String folderName) {
		File folder = new File(new File("").getAbsoluteFile(), "target" + File.separator + folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		return folder;
	}
}