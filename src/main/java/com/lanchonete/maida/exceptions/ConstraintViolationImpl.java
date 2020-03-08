package com.lanchonete.maida.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

public class ConstraintViolationImpl<T> implements ConstraintViolation<T> {

	private String message;
	private String messageTemplate;
	private T t;



	private ConstraintViolationImpl(String message, String messageTemplate, T t) {
		super();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getExecutableParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getExecutableReturnValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getPropertyPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getInvalidValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ConstraintDescriptor getConstraintDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object unwrap(@SuppressWarnings("rawtypes") Class type) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> ConstraintViolationImpl<T> of(String message, String messageTemplete, T value) {
		return new ConstraintViolationImpl<T>(message, messageTemplete, value);
	}

}
