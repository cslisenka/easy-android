package by.easyandroid.service.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestFileUtil {

	@Test
	public void testCreateTempDirectory() throws IOException {
		File tempDir = FileUtil.createTempDirectory();
		Assert.assertTrue(tempDir.exists());
		Assert.assertTrue(tempDir.isDirectory());
		Assert.assertEquals(0, tempDir.list().length);
		FileUtils.deleteDirectory(tempDir);
	}
}	
