package by.easyandroid.framework;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class ExecutionCheckingFakeTask implements ITask {

	private boolean isExecuted = false;
	
	public void execute() throws TaskExecutionException {
		isExecuted = true;
	}

	public boolean isExecuted() {
		return isExecuted;
	}
	
	public void reset() {
		isExecuted = false;
	}
}