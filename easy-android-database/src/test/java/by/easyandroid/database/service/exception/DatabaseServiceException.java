package by.easyandroid.database.service.exception;

public class DatabaseServiceException extends Exception {

	public DatabaseServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseServiceException(String message) {
		super(message);
	}

	public DatabaseServiceException(Throwable cause) {
		super(cause);
	}
}