package by.easyandroid.framework;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import by.easyandroid.framework.exception.TaskExecutionException;

public class TestTaskExecutor {

	private TaskExecutor executor;
	private ExecutionCheckingFakeTask checkingTask;
	
	@Before
	public void setUp() {
		executor = new TaskExecutor();
	}
	
	@Test
	public void testExecuteConsoleTask() throws TaskExecutionException {
		checkingTask = new ExecutionCheckingFakeTask();
		Assert.assertFalse(checkingTask.isExecuted());
		executor.addTask(checkingTask);
		executor.executeAll();
		Assert.assertTrue(checkingTask.isExecuted());
	}
	
	@Test
	public void testClearTaskListAfterExecuting() throws TaskExecutionException {
		checkingTask = new ExecutionCheckingFakeTask();
		Assert.assertFalse(checkingTask.isExecuted());
		executor.addTask(checkingTask);
		executor.executeAll();
		Assert.assertTrue(checkingTask.isExecuted());
		
		// If we execute all tasks second time, no tasks would be executed
		checkingTask.reset();
		executor.removeAllTasks();
		executor.executeAll();
		Assert.assertFalse(checkingTask.isExecuted());
	}	
}