package by.easyandroid.framework;
import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.exception.TaskExecutionException;

public class TestCopier extends AbstractTestSourceDestDirBase {

	private Copier copier;
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		copier = new Copier();
		copier.setOutputDir(destinationPath);
	}
	
	@Test
	public void testCopier() throws TaskExecutionException, IOException {
		// TODO copy folder contents and copy folder with content!
		//copier.add(sourcePath + File.separator + "sourceSubdir1/*");
		//copier.addContent(sourcePath + File.separator + "sourceSubdir1");
		
		// TODO implement getting files/folders from web url
		
		// Copy sourceSubdir1 contents to working directory
		copier.add(sourcePath + File.separator + "sourceSubdir1");
		
		// Copy sourceFile.txt to working directory
		copier.add(sourcePath + File.separator + "sourceFile.txt");
		
		// Copy sourceFile.txt to "working directory/subfolder"
		copier.add(sourcePath + File.separator + "sourceFile.txt", "subfoler");

		// Copy sourceSubdir1 contents to "working directory/subfolder2"
		copier.add(sourcePath + File.separator + "sourceSubdir1", "subfoler2");
		
		// Add text file in memory
		copier.createFile("outputSubPath" + File.separator + "test.txt", "text file content");
		
		// Create folder "subdircreated" in working directory
		copier.createFolder("subdircreated");
		
		// Save all to working directory
		copier.flush();
		
		assertFileExistsDestPath("sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("sourceFile.txt");
		assertFileExistsDestPath("subfoler/sourceFile.txt");
		assertFileExistsDestPath("subfoler2/sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("outputSubPath/test.txt");
		assertFileContentDestPath("outputSubPath/test.txt", "text file content");
		assertFileExistsDestPath("subdircreated");
	}
	
	@Test
	public void testCleanWorkingDirectoryBeforeTaskExecution() throws IOException, TaskExecutionException {
		// Create some file in working directory
		File testFile = new File(destinationPath + File.separator + "testfile");
		testFile.createNewFile();
		Assert.assertTrue(testFile.exists());
		
		copier.createFile("test.txt", "testfile content");
		copier.flush();
		
		Assert.assertFalse(testFile.exists());
	}
	
	@Test
	public void testOverwritingFilesAndFolders() throws TaskExecutionException, IOException {
		copier.add(sourcePath + File.separator + "sourceSubdir1");
		copier.add(sourcePath + File.separator + "sourceDirWithDuplicatedContent");
		copier.flush();
		
//		assertFileExistsDestPath("sourceSubdir2/sourceFileInSubdir.txt");
//		assertFileExistsDestPath("sourceSubdir2/folderInDuplicatedSubdir");
		
		copier.add(sourcePath + File.separator + "sourceSubdir1");
		copier.add(sourcePath + File.separator + "sourceDirWithSameSibdirectories");
		copier.flush();
		assertFileContentDestPath("sourceSubdir2/sourceFileInSubdir.txt", "sourceFileInSubdir.txt contents overwrite");
	}
}