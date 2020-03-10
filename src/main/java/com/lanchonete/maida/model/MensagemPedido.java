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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mensagem_pedido")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
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
	@Basic(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pedido pedido;
	
}
