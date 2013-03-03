package by.easyandroid.framework;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractTestSourceDestDirBase {

	protected String testResourcesPath;
	protected String sourcePath;
	protected String destinationPath;
	private File workingDirectory;
	
	@Before
	public void setUp() throws IOException {
		String basePath = new File("").getAbsolutePath();
		testResourcesPath = basePath + File.separator + "src" + 
				File.separator + "test" + File.separator + 
				"resources";
		
		sourcePath = testResourcesPath + File.separator + "sourceDir";
		destinationPath = basePath + File.separator + "target" + File.separator + "testCopier";
		
		// Recreate working directory for testing
		workingDirectory = new File(destinationPath);
		FileUtils.deleteDirectory(workingDirectory);
		workingDirectory.mkdirs();
	}
	
	public void assertFileContentDestPath(String path, String content) throws IOException {
		File file = new File(destinationPath + File.separator + path.replace("/", File.separator));
		Assert.assertEquals(content, FileUtils.readFileToString(file));
	}
	
	public void assertFileExistsDestPath(String path) {
		Assert.assertTrue(new File(destinationPath + File.separator + path.replace("/", File.separator)).exists());
	}
	
	@After
	public void tearDown() throws IOException {
		FileUtils.deleteDirectory(workingDirectory);
	}	
}