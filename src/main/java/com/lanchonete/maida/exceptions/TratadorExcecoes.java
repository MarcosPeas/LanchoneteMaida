package com.lanchonete.maida.exceptions;

import java.util.Set;

import javax.mail.AuthenticationFailedException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lanchonete.maida.controller.AuthController;
import com.lanchonete.maida.response.Response;

@RestControllerAdvice
public class TratadorExcecoes extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.erros("Verifique se os dados estão no formato correto"));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.erros("Método não permitido"));
	}

	// ConstraintViolationException
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

	// ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response<Object> processarErros404(final ResourceNotFoundException ex) {
		return Response.erros(ex.getMessage());
	}

	// UsernameNotFoundException
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
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
	/*
	 * @ExceptionHandler(HttpMessageNotReadableException.class)
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST) public Response<Object>
	 * processarHttpMessageNotReadableException(final
	 * HttpMessageNotReadableException ex) { return
	 * Response.erros("Verifique se os dados estão no formato correto"); }
	 */

	// SQLIntegrityConstraintViolationException
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarSQLIntegrityConstraintViolationException(
			final DataIntegrityViolationException ex) {
		String message = ex.getMostSpecificCause().toString();
		System.out.println("message: " + ex.getMostSpecificCause());
		if (message.contains("email")) {
			return Response.erros("e-mail já cadastrado");
		} else if (message.contains("foreign key")) {
			if (message.contains("item_pedido")) {
				return Response.erros(
						"para realizar esta operação, é necessário remover os itens dos pedidos vinculados a esta entidade");
			} else if (message.contains("pedido")) {
				return Response.erros(
						"para realizar esta operação, é necessário remover os pedidos vinculados a esta entidade");
			}
		}
		ex.printStackTrace();
		return Response.erros("Erro de integridade do banco");
	}

	// ConversionFailedException
	@ExceptionHandler(ConversionFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarConversionFailedException(final ConversionFailedException ex) {
		return Response.erros("Erro ao converter dado");
	}

	// AuthenticationFailedException
	@ExceptionHandler(AuthenticationFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarAuthenticationFailedException(final AuthenticationFailedException ex) {
		log.info("\nPara usar esta funcionalidade, configure o serviço de e-mails no arquivo application.properties"
				+ "\nPor segurança, não envie suas credenciais de e-mails para repositórios públicos");
		return Response.erros("O provedor de e-mails falhou");
	}

	// NumberFormatException
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<Object> processarNumberFormatException(final NumberFormatException ex) {
		return Response.erros("Verifique se está colocando texto em campos numéricos");
	}
	
	
	// RequestRejectedException
	@ExceptionHandler(RequestRejectedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Response<Object> processarRequestRejectedException(final RequestRejectedException ex) {
		return Response.erros("Ocorreu um erro no servido :(");
	}
}
