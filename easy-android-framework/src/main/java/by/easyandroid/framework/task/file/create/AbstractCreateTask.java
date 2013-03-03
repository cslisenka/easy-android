package by.easyandroid.framework.task.file.create;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public abstract class AbstractCreateTask implements ITask {

	protected String path;
	
	public AbstractCreateTask(String path) {
		this.path = path;
	}	
	
	public abstract void execute() throws TaskExecutionException;
}