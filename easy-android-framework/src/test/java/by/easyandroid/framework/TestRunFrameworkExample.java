package by.easyandroid.framework;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.exception.TaskExecutionException;

public class TestRunFrameworkExample extends AbstractTestSourceDestDirBase {

	private Copier copier;
	
	@Before
	public void setUp() {
		super.setUp();
		copier = new Copier();
	}
	
	@Test
	public void testRun() throws TaskExecutionException, IOException {
		copier.setOutputDir(destinationPath);
		
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
		copier.createFile("outputSubPath/test.txt", "text file content");
		
		// Create folder "subdircreated" in working directory
		copier.createFolder("subdircreated");
		
		// Save all to working directory
		copier.flush();
		
		// TODO test correctly overwriting
		
		assertFileExistsDestPath("sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("sourceFile.txt");
		assertFileExistsDestPath("subfoler/sourceFile.txt");
		assertFileExistsDestPath("subfoler2/sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("outputSubPath/test.txt");
		assertFileContentDestPath("outputSubPath/test.txt", "text file content");
		assertFileExistsDestPath("subdircreated");
	}
}