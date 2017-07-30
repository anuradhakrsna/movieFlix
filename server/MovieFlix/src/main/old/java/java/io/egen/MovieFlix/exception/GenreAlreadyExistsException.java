package io.egen.MovieFlix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GenreAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1604040924269378994L;

	public GenreAlreadyExistsException(String message) {
		super(message);
	}

	public GenreAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
