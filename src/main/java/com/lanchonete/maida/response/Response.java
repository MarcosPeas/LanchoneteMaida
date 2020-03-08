package com.lanchonete.maida.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private T data;
	private List<String> erros;

	private Response() {
		erros = new ArrayList<>();
	}

	private Response(T data) {
		this.data = data;
	}

	private Response(String... erros) {
		this.erros = new ArrayList<>();
		this.erros.addAll(Arrays.asList(erros));
	}

	public static <T> Response<T> of(T value) {
		return new Response<T>(value);
	}

	public static <T> Response<T> erro(String... erros) {
		return new Response<>(erros);
	}

	public static <T> Response<T> instance() {
		return new Response<>();
	}
}
