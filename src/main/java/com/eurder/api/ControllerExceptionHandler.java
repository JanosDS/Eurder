package com.eurder.api;

import com.eurder.exception.InvalidInputException;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MandatoryFieldException.class)
	private void mandatoryFieldException(MandatoryFieldException exception, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	}
	@ExceptionHandler(InvalidInputException.class)
	private void invalidInputException(InvalidInputException exception, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	private void unauthorizedExceptionException(UnauthorizedException exception, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
	}

}
