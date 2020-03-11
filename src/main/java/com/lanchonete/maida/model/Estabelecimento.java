package com.lanchonete.maida.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estabelecimento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Informe o nome do estabelecimento")
	@Size(min = 5, max = 30, message = "O nome do estabelecimento deve conter de 5 a 30 caracteres")
	private String nome;

	@OneToOne
	@NotNull(message = "O gestor do estabelecimento não pode ser nulo")
	private Usuario gestor;
}
