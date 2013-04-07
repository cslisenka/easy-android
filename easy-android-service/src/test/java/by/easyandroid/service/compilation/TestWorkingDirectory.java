package by.easyandroid.service.compilation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import by.easyandroid.service.compilation.util.WorkingDirectory;

public class TestWorkingDirectory {

	@Test
	public void testCreateTempDirectory() throws IOException {
		File tempDir = WorkingDirectory.createTempDirectory();
		Assert.assertTrue(tempDir.exists());
		Assert.assertTrue(tempDir.isDirectory());
		Assert.assertEquals(0, tempDir.list().length);
		FileUtils.deleteDirectory(tempDir);
	}
}	
