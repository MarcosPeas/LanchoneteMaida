package com.lanchonete.maida.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mensagem_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "O título não pode ser nulo")
	private String titulo;

	@NotBlank(message = "O texto da mensagem não pode ser nula")
	private String texto;
	private LocalDateTime horario;

	@PrePersist
	private void definirHorarioMensagem() {
		horario = LocalDateTime.now();
	}

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@NotNull(message = "O pedido não pode ser nulo")
	private Pedido pedido;
}
