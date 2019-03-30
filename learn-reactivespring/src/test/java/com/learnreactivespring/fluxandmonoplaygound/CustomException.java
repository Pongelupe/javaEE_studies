package com.learnreactivespring.fluxandmonoplaygound;

public class CustomException extends Throwable {

	private static final long serialVersionUID = -609981367683062694L;
	private String message;

	public CustomException(Throwable e) {
		this.message = e.getMessage();
	}

	public String getMessage() {
		return message;
	}

}
