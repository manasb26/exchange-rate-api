package com.exchangerate.handlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.exchangerate.exceptions.InvalidDateException;
import com.exchangerate.models.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
	private ErrorResponse errorResponse;

	public CustomExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(HttpClientErrorException exception) {
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTitle("Invalid Currency Type");
		errorResponse.setDescription(exception.getMessage());
		errorResponse.setTimeStamp(getCurrentDateTime());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(InvalidDateException exception) {
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTitle("Invalid Date Range. Date format: yyyy-mm-dd");
		errorResponse.setDescription(exception.getMessage());
		errorResponse.setTimeStamp(getCurrentDateTime());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(DateTimeParseException exception) {
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTitle("Invalid Date Format. Date format: yyyy-mm-dd");
		errorResponse.setDescription(exception.getMessage());
		errorResponse.setTimeStamp(getCurrentDateTime());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTitle("Invalid request.");
		errorResponse.setDescription(exception.getMessage());
		errorResponse.setTimeStamp(getCurrentDateTime());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private String getCurrentDateTime() {
		DateFormat simple = new SimpleDateFormat("dd MM yyyy HH:mm:ss:SSS Z");
		Date result = new Date(System.currentTimeMillis());
		return simple.format(result);
	}
}
