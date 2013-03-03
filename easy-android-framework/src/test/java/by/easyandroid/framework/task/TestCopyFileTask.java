package by.easyandroid.framework.task;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.AbstractTestSourceDestDirBase;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.file.CopyFileTask;

public class TestCopyFileTask extends AbstractTestSourceDestDirBase {

	private CopyFileTask copyFileTask;
	
	@Before
	public void setUp() {
		super.setUp();
		// TODO add slashes replacing
		copyFileTask = new CopyFileTask(sourcePath + File.separator + "sourceFile.txt", destinationPath + File.separator + "subdir");
	}
	
	@Test
	public void testCopy() throws TaskExecutionException {
		copyFileTask.execute();
		
		assertFileExistsDestPath("subdir/sourceFile.txt");
	}
}