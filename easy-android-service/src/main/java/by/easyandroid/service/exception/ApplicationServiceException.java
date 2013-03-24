package by.easyandroid.service.exception;

/**
 * Throwed if something wrong happens in service
 * @author kslisenko
 *
 */
public class ApplicationServiceException extends Exception {

	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationServiceException(String message) {
		super(message);
	}

	public ApplicationServiceException(Throwable cause) {
		super(cause);
	}
}
