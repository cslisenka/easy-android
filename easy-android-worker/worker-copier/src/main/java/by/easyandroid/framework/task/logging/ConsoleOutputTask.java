package by.easyandroid.framework.task.logging;

import by.easyandroid.framework.task.ITask;

// TODO implement print stream output task
public class ConsoleOutputTask implements ITask {

	private String stringToOut;
	
	public ConsoleOutputTask(String stringToOut) {
		this.stringToOut = stringToOut;
	}

	public void execute() {
		System.out.println(stringToOut);
	}
}