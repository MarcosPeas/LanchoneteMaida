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
	private String nome;

	@ToString.Include
	private String email;

	@Column(name = "data_nascimento")
	@ToString.Include
	private LocalDate dataNascimento;

	@ToString.Include
	private String telefone;

	@Basic(fetch = FetchType.LAZY)
	private String senha;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	@PrePersist
	private void encriptarSenha() {
		senha = SenhaUtil.encriptarSenha(senha);
	}

	public enum Perfil {
		ROLE_ADMIN, ROLE_GESTOR, ROLE_CLIENTE
	}
}
