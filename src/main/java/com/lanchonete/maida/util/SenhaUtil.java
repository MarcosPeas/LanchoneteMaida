package com.lanchonete.maida.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {
	private SenhaUtil() {
	}

	public static String encriptarSenha(String senha) {
		if (senha == null || senha.isBlank()) {
			return senha;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(senha);
	}

	public static boolean validarSenha(String senha, String senhaEncriptada) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(senha, senhaEncriptada);
	}
}
