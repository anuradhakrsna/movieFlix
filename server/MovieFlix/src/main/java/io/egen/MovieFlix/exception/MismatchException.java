package io.egen.MovieFlix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MismatchException extends RuntimeException {

	private static final long serialVersionUID = 9124038480596109999L;

	public MismatchException(String message) {
		super(message);
	}

	public MismatchException(String message, Throwable cause) {
		super(message, cause);
	}
}