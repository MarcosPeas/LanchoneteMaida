package com.lanchonete.maida.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime horarioPedido;
	private LocalDateTime horarioAceito;
	private LocalDateTime horarioEntregue;
	private BigDecimal valor;
	private String formaPagamento;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario cliente;
	private StatusPedido status;

	@OneToMany
	private List<ItemPedido> itensPedido;

	@OneToMany
	private List<MensagemPedido> mensagens;

	public enum StatusPedido {
		SOLICITADO, RECEBIDO, EM_PREPARO, RECUSADO, CANCELADO, EM_ENTREGA, FINALIZADO
	}
}
