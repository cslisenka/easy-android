package by.easyandroid.framework.task;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.AbstractTestSourceDestDirBase;
import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.file.copy.CopyFileTask;

public class TestCopyFileTask extends AbstractTestSourceDestDirBase {

	private CopyFileTask copyFileTask;
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		copyFileTask = new CopyFileTask(sourcePath + File.separator + "sourceFile.txt", destinationPath + File.separator + "subdir");
	}
	
	@Test
	public void testCopy() throws TaskExecutionException {
		copyFileTask.execute();
		assertFileExistsDestPath("subdir/sourceFile.txt");
	}
}