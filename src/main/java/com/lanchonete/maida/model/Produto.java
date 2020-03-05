package com.lanchonete.maida.model;

import java.math.BigDecimal;

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
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
	private boolean disponivel;

	@Enumerated(EnumType.STRING)
	private ProdutoTipo tipo;

	public enum ProdutoTipo {
		COMIDA, BEBIDA, SOBREMESA
	}
}
