package com.lanchonete.maida.exceptions;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lanchonete.maida.response.Response;

import javassist.NotFoundException;

@RestControllerAdvice
public class TratadorExcecoes extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processaErrosValidacao(final ConstraintViolationException ex) {
		Response<Object> response = Response.instance();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		violations.forEach(m -> {
			response.getErros().add(m.getMessageTemplate());
		});
		return response;
	}
	
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response<Object> processaErros404(final NotFoundException ex) {
		return Response.erro(ex.getMessage());
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}
	//NotFoundException

}
