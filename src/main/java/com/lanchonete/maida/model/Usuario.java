package com.lanchonete.maida.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	private String telefone;
	private String senha;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	public enum Perfil {
		ADMIN, GESTOR, CLIENTE
	}
}
