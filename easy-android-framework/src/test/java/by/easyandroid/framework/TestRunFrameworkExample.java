package by.easyandroid.framework;
import java.io.File;

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
	public void testRun() throws TaskExecutionException {
		copier.setOutputDir(destinationPath);
		
		// TODO copy folder contents and copy folder with content!
		//copier.add(sourcePath + File.separator + "sourceSubdir1/*");
		//copier.addContent(sourcePath + File.separator + "sourceSubdir1");
		
		// Copy sourceSubdir1 contents to working directory
		copier.add(sourcePath + File.separator + "sourceSubdir1");
		
		// Copy sourceFile.txt to working directory
		copier.add(sourcePath + File.separator + "sourceFile.txt");
		
		// Copy sourceFile.txt to "working directory/subfolder"
		copier.add(sourcePath + File.separator + "sourceFile.txt", "subfoler");

		// Copy sourceSubdir1 contents to "working directory/subfolder2"
		copier.add(sourcePath + File.separator + "sourceSubdir1", "subfoler2");
		
//		// Add text file in memory
//		copier.createFile("text file content", "outputPath/test.xml");
//		
//		copier.createFolder("outputPath/subdir");
		
		// Save all to working directory
		copier.flush();
		
		// TODO test correctly overwriting
		
		assertFileExistsDestPath("sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("sourceFile.txt");
		assertFileExistsDestPath("subfoler/sourceFile.txt");
		assertFileExistsDestPath("subfoler2/sourceSubdir2/sourceFileInSubdir.txt");
	}
}