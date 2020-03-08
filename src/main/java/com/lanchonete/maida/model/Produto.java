package com.lanchonete.maida.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "O título não pode ser vazio")
	private String titulo;
	
	@NotBlank(message = "Informe a descrição do produto")
	private String descricao;
	
	private BigDecimal valor;
	private boolean disponivel;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo do produto não pode ser nulo")
	private ProdutoTipo tipo;

	public enum ProdutoTipo {
		COMIDA, BEBIDA, SOBREMESA
	}
}
