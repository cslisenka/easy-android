package by.easyandroid.framework.task;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.AbstractTestSourceDestDirBase;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.file.CopyDirectoryTask;

public class TestCopyDirectoryTask extends AbstractTestSourceDestDirBase {

	private CopyDirectoryTask copyDirectoryTask;
	
	@Before
	public void setUp() {
		super.setUp();
		copyDirectoryTask = new CopyDirectoryTask(sourcePath, destinationPath);
	}
	
	@Test
	public void testCopy() throws TaskExecutionException {
		copyDirectoryTask.execute();
		
		assertFileExistsDestPath("sourceSubdir1/sourceSubdir2/sourceFileInSubdir.txt");
		assertFileExistsDestPath("sourceFile.txt");		
	}
}