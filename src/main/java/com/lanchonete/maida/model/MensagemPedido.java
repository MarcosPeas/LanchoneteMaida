package com.lanchonete.maida.model;

import java.time.LocalDateTime;

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
@Table(name = "mensagem_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String texto;
	private LocalDateTime horario;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
}
