package com.lanchonete.maida.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioToken {
	private String token;
	private Usuario usuario;
}
