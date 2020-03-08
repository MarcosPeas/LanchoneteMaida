package com.lanchonete.maida.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private String nome;

	@OneToOne
	@NotNull(message = "O gestor do estabelecimento não pode ser nulo")
	private Usuario gestor;
}
