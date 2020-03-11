package com.lanchonete.maida.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime horarioPedido;
	private LocalDateTime horarioAceito;
	private LocalDateTime horarioEntregue;
	
	@NotNull(message = "O valor do pedido não pode ser nulo")
	@DecimalMin(value = "0.05", inclusive = true, message = "Não é possível realizar um pedido com o valor menor que R$ 0,05")
	private BigDecimal valor;

	@NotBlank(message = "Informe a forma de pagamento")
	@Size(max = 30, message = "A forma de pagamento não pode conter mais que 30 caracteres")
	private String formaPagamento;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@NotNull(message = "O cliente não pode ser nullo")
	private Usuario cliente;

	@NotNull(message = "O status não pode ser nullo")
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@Basic(fetch = FetchType.EAGER)
	private List<ItemPedido> itensPedido;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@Basic(fetch = FetchType.EAGER)
	private List<MensagemPedido> mensagens;

	@PrePersist
	private void definirHorarioPedido() {
		horarioPedido = LocalDateTime.now();
	}

	public enum StatusPedido {
		SOLICITADO, RECEBIDO, EM_PREPARO, RECUSADO, CANCELADO, EM_ENTREGA, FINALIZADO
	}
}
