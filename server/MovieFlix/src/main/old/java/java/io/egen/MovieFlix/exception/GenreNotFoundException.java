package io.egen.MovieFlix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6579806890282180143L;

	public GenreNotFoundException(String message) {
		super(message);
	}

	public GenreNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
