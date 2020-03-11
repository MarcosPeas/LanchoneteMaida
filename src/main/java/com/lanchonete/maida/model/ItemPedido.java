package com.lanchonete.maida.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@DecimalMin(value = "0.05", inclusive = true, message = "O valor unitário do item de um pedido não pode ser menor que R$ 0,05")
	@DecimalMax(value = "10000.00", inclusive = true, message = "O valor unitário do item de um pedido não pode ser maior que R$ 10.000,00")
	@NotNull(message = "Informe o valor unitário do item do pedido")
	private BigDecimal valorUnitario;
	
	@NotNull(message = "O nome do produto no item do pedido não pode ser nulo")
	@Size(min = 3, max = 30, message = "O nome do produto no item do pedido deve conter de 3 a 30 caracteres")
	@Column(name = "nome_produto")
	private String nomeProduto;
	
	@Min(value = 1, message = "A quantidade de itens deve ser no mínimo 1")
	@NotNull(message = "Informe a quantidade de itens do pedido")
	private int quantidade;
	
	@NotNull(message = "Informe o valor total do item do pedido")
	@DecimalMin(value = "0.05", inclusive = true, message = "O valor total do item de um pedido não pode ser menor que R$ 0,05")
	private BigDecimal valorTotal;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@Basic(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
}
