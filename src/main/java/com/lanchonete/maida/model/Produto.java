package com.lanchonete.maida.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull(message = "O título não pode ser nulo")
	@Size(min = 3, max = 30, message = "O título do produto deve conter de 3 a 30 caracteres")
	private String titulo;

	@NotNull(message = "Informe a descrição do produto")
	@Size(min = 10, max = 255, message = "A descrição do produto deve conter de 10 a 255 caracteres")
	private String descricao;

	
	@NotNull(message = "Informe o valor do produto")
	@DecimalMin(value = "0.05", inclusive = true, message = "O valor do produto não pode ser menor que R$ 0,05")
	@DecimalMax(value = "10000.00", inclusive = true, message = "O valor do produto não pode ser maior que R$ 10.000,00")
	private BigDecimal valor;
	
	private boolean disponivel;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O tipo do produto não pode ser nulo")
	private ProdutoTipo tipo;

	public enum ProdutoTipo {
		COMIDA, BEBIDA, SOBREMESA
	}
}
