package com.lanchonete.maida.exceptions;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lanchonete.maida.response.Response;

import javassist.NotFoundException;

@RestControllerAdvice
public class TratadorExcecoes /* extends ResponseEntityExceptionHandler */ {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarErrosValidacao(final ConstraintViolationException ex) {
		Response<Object> response = Response.instance();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		violations.forEach(m -> {
			response.getErros().add(m.getMessageTemplate());
		});
		return response;
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response<Object> processarErros404(final NotFoundException ex) {
		return Response.erros(ex.getMessage());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarUsernameNotFoundException(final UsernameNotFoundException ex) {
		return Response.erros(ex.getMessage());
	}

	// AuthenticationCredentialsNotFoundException
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarAuthenticationCredentialsNotFoundException(
			final AuthenticationCredentialsNotFoundException ex) {
		return Response.erros(ex.getMessage());
	}
	
	// DateTimeParseException
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarDateTimeParseException(
			final HttpMessageNotReadableException ex) {
		return Response.erros("Verifique se os dados estão no formato correto");
	}
	
	//HttpMessageNotReadableException -> não consegue converter palavra errada para enum

}
