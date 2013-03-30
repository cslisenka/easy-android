package by.easyandroid.framework;

import java.util.ArrayList;
import java.util.List;

import by.easyandroid.framework.exception.TaskExecutionException;
import by.easyandroid.framework.task.ITask;

public class TaskExecutor {

	private List<ITask> taskList = new ArrayList<ITask>();
	
	public void addTask(ITask task) {
		taskList.add(task);
	}
	
	public void executeAll() throws TaskExecutionException {
		for (ITask task : taskList) {
			task.execute();
		}
	}
	
	public void removeAllTasks() {
		taskList.clear();
	}
}