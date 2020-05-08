package com.exchangerate.exceptions;

public class InvalidCurrencyException extends RuntimeException {

	private static final long serialVersionUID = -7173113870898277705L;

	public InvalidCurrencyException(String msg) {
		super(msg);
	}
}
