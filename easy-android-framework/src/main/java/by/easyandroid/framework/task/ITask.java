package by.easyandroid.framework.task;

import by.easyandroid.framework.exception.TaskExecutionException;

public interface ITask {

	void execute() throws TaskExecutionException;
}