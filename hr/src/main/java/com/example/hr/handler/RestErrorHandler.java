package com.example.hr.handler;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.dto.RestErrorMessage;
import com.example.hr.service.exception.BaseException;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleBaseException(BaseException e) {
		return new RestErrorMessage(e.getMessage(), e.getCode().name(), e.getDebug());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
	public RestErrorMessage handleRuntimeException(RuntimeException e) {
		return new RestErrorMessage(e.getMessage(), e.getClass().getSimpleName(), Arrays.toString(e.getStackTrace()));
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleValidationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("|"));
		return new RestErrorMessage(message, message, "d5d72cb3-5541-4cd9-baec-487cba94c9b8");
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		return new RestErrorMessage(message, message, "d5d72cb3-5541-4cd9-baec-487cba94c9b8");
	}
}
