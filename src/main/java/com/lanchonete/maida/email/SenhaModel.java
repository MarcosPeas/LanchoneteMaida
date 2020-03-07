package com.lanchonete.maida.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenhaModel {
	private String token;
	private String senha1;
	private String senha2;
}
