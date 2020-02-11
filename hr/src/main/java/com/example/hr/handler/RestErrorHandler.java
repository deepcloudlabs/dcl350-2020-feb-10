package com.example.hr.handler;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
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
}
