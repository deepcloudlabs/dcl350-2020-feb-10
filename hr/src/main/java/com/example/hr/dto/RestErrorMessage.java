package com.example.hr.dto;

public class RestErrorMessage {
	private String message;
	private String code;
	private String debug;

	public RestErrorMessage(String message, String code, String debug) {
		this.message = message;
		this.code = code;
		this.debug = debug;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public String getDebug() {
		return debug;
	}

}
