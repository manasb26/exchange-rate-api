package com.exchangerate.exceptions;

public class NotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8667392405681461699L;

	public NotFoundException(String message){
		super(message);
	}

}
