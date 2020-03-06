package com.lanchonete.maida.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private T data;
	private List<String> erros;

	private Response(T data) {
		this.data = data;
	}

	public static <T> Response<T> of(T value) {
		return new Response<T>(value);
	}
}
