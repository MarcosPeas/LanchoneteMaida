package com.lanchonete.maida.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.lanchonete.maida.util.SenhaUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Include
	private int id;

	@ToString.Include
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;

	@ToString.Include
	@NotBlank(message = "O e-mail não pode ser vazio")
	@Email(message = "Informe um e-mail válido")
	private String email;

	@Column(name = "data_nascimento")
	@ToString.Include
	@NotNull(message = "Informe a data de nascimento")
	private LocalDate dataNascimento;

	@ToString.Include
	private String telefone;

	@Basic(fetch = FetchType.LAZY)
	@Length(min = 6, message = "A senha deve conter no mínimo seis (6) caracteres")
	private String senha;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O perfil não pode ser nulo")
	private Perfil perfil;

	@PrePersist
	private void encriptarSenha() {
		senha = SenhaUtil.encriptarSenha(senha);
	}

	public enum Perfil {
		ROLE_ADMIN, ROLE_GESTOR, ROLE_CLIENTE
	}
}
