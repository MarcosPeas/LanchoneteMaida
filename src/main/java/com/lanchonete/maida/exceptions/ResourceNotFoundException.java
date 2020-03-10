package com.lanchonete.maida.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 3442456867286711580L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
