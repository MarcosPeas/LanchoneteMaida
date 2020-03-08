package com.lanchonete.maida.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lanchonete.maida.response.Response;

@RestControllerAdvice
public class ValidadorController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processValidationError(final ConstraintViolationException ex) {
		Response<Object> response = Response.instance();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		violations.forEach(m -> {
			response.getErros().add(m.getMessageTemplate());
		});
		return response;
	}

}
