package by.easyandroid.framework.task.file.copy;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public abstract class AbstractCopyTask implements ITask {

	protected String from;
	protected String to;
	
	public AbstractCopyTask(String from, String to) {
		this.from = from;
		this.to = to;
	}	
	
	public abstract void execute() throws TaskExecutionException;
}
