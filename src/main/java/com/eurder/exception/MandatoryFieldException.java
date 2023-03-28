package com.eurder.exception;

import org.webjars.NotFoundException;

public class MandatoryFieldException extends NotFoundException {
	public MandatoryFieldException(String message) {
		super(message);
	}
}
