package by.easyandroid.framework;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.logging.ConsoleOutputTask;

public class TestTaskExecutor {

	private TaskExecutor executor;
	
	@Before
	public void setUp() {
		executor = new TaskExecutor();
	}
	
	@Test
	public void testExecuteConsoleTask() throws TaskExecutionException {
		// TODO check if task executed
		executor.addTask(new ConsoleOutputTask("Hello World!"));
		executor.executeAll();
	}
	
	// TODO check clear all tasks
}