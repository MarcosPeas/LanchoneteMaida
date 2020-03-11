package com.lanchonete.maida.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mensagem_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "O título da mensagem não pode ser nulo")
	@Size(min = 3, max = 30, message = "O título da mensagem deve conter de 3 a 30 caracteres")
	private String titulo;

	@NotNull(message = "O texto da mensagem não pode ser nulo")
	@Size(min = 3, max = 255, message = "O texto da mensagem deve conter de 3 a 255 caracteres")
	private String texto;

	private LocalDateTime horario;

	@PrePersist
	private void definirHorarioMensagem() {
		horario = LocalDateTime.now();
	}

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@Basic(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pedido pedido;

}
