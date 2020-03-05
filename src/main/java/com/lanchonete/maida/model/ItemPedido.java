package com.lanchonete.maida.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private BigDecimal valorUnitario;
	private int quantidade;
	private BigDecimal valorTotal;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
}
