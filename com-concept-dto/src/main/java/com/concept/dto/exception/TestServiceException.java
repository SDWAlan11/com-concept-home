package com.concept.dto.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class TestServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959181809189857968L;

	public TestServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestServiceException(String message) {
		super(message);
	}
	
	

}
