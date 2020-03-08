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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message = "Informe a forma de pagamento")
	private String formaPagamento;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@NotNull(message = "O cliente não pode ser nullo")
	private Usuario cliente;

	@NotNull(message = "O status não pode ser nullo")
	private StatusPedido status;

	@OneToMany
	private List<ItemPedido> itensPedido;

	@OneToMany
	private List<MensagemPedido> mensagens;

	@PrePersist
	private void definirHorarioPedido() {
		horarioPedido = LocalDateTime.now();
	}

	public enum StatusPedido {
		SOLICITADO, RECEBIDO, EM_PREPARO, RECUSADO, CANCELADO, EM_ENTREGA, FINALIZADO
	}
}
