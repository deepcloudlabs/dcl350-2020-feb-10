package com.example.hr.service.exception;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
	private final ErrorCode code;
	private final String debug;

	public BaseException(String message, ErrorCode code, String debug) {
		super(message);
		this.code = code;
		this.debug = debug;
	}

	public ErrorCode getCode() {
		return code;
	}

	public String getDebug() {
		return debug;
	}

}
