package by.easyandroid.framework.exception;

public class TaskExecutionException extends Exception {

	public TaskExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskExecutionException(Throwable cause) {
		super(cause);
	}
}
