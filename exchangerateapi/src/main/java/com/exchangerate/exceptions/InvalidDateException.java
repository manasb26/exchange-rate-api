package com.exchangerate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateException extends RuntimeException {

	private static final long serialVersionUID = 4480102350614676608L;

	public InvalidDateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
