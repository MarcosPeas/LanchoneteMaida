package com.lanchonete.maida.exceptions;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

public class ConstraintViolationImpl<T> implements ConstraintViolation<T> {

	private String message;
	private String messageTemplate;
	private T t;

	private ConstraintViolationImpl(String message, String messageTemplate, T t) {
		this.message = message;
		this.messageTemplate = messageTemplate;
		this.t = t;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getMessageTemplate() {
		return messageTemplate;
	}

	@Override
	public T getRootBean() {
		return t;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getRootBeanClass() {
		return t.getClass();
	}

	@Override
	public Object getLeafBean() {
		return null;
	}

	@Override
	public Object[] getExecutableParameters() {
		return null;
	}

	@Override
	public Object getExecutableReturnValue() {
		return null;
	}

	@Override
	public Path getPropertyPath() {
		return null;
	}

	@Override
	public Object getInvalidValue() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ConstraintDescriptor getConstraintDescriptor() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object unwrap(@SuppressWarnings("rawtypes") Class type) {
		return null;
	}

	public static <T> ConstraintViolationImpl<T> of(String message, String messageTemplete, T value) {
		return new ConstraintViolationImpl<T>(message, messageTemplete, value);
	}

	public ConstraintViolationException getViolationException() {
		Set<ConstraintViolationImpl<T>> erros = new HashSet<>();
		erros.add(this);
		return new ConstraintViolationException(erros);
	}

}
