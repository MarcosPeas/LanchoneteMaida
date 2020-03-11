package com.lanchonete.maida.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lanchonete.maida.util.SenhaUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "O nome não pode ser nulo")
	@Size(min = 3, max = 80, message = "O texto da mensagem deve conter de 3 a 80 caracteres")
	private String nome;

	@NotBlank(message = "O e-mail não pode ser nulo")
	@Email(message = "Informe um e-mail válido")
	@Size(max = 50, message = "O e-mail não pode conter mais do que 50 caracteres")
	private String email;

	@Column(name = "data_nascimento")
	@NotNull(message = "Informe a data de nascimento")
	private LocalDate dataNascimento;

	@Pattern(regexp = "^\\(?(\\d{2})\\)?[  ]?(\\d{5})[- ]?(\\d{4})$", message = "Informe um número de telefone com o formato: xx xxxxx-xxxx ou xx xxxxxxxxx ou xxxxxxx-xxxx ou xxxxxxxxxxx")
	@NotNull(message = "Informe o telefone")
	private String telefone;

	@NotNull(message = "Informe a senha")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O perfil não pode ser nulo")
	private Perfil perfil;

	@PrePersist
	private void encriptarSenha() {
		senha = SenhaUtil.encriptarSenha(senha);
	}

	public enum Perfil {
		/* ROLE_ADMIN, */ ROLE_GESTOR, ROLE_CLIENTE
	}
}
