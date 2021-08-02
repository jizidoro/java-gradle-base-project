package com.comrades.api.exceptions;

public class BeanException extends RuntimeException {
	private static final long serialVersionUID = -8007768579199036346L;

	public BeanException() {

	}

	public BeanException(String message) {
		super(message);

	}

	public BeanException(Throwable cause) {
		super(cause);

	}

	public BeanException(String message, Throwable cause) {
		super(message, cause);

	}

	public BeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
