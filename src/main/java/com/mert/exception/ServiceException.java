package com.mert.exception;

public class ServiceException extends RuntimeException {
	
	
	private int errorCode;

	public ServiceException(int errorCode) {

		this.errorCode = errorCode;
	}

	public ServiceException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {

		return "service." + errorCode;
	}

	@Override
	public String getMessage() {

		String msg = super.getMessage();

		if (msg == null)
			msg = getErrorCode();

		return msg;
	}

	@Override
	public String toString() {

		return "ServiceException errorCode:" + errorCode;
	}
}
