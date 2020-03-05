package com.lanchonete.maida.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

	private int id;
	private LocalDateTime horarioPedido;
	private LocalDateTime horarioAceito;
	private LocalDateTime horarioEntregue;
	private BigDecimal valor;
	private String formaPagamento;
	private Usuario cliente;
	private StatusPedido status;
	private List<ItemPedido> itensPedido;
	private List<MensagemPedido> mensagens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getHorarioPedido() {
		return horarioPedido;
	}

	public void setHorarioPedido(LocalDateTime horarioPedido) {
		this.horarioPedido = horarioPedido;
	}

	public LocalDateTime getHorarioAceito() {
		return horarioAceito;
	}

	public void setHorarioAceito(LocalDateTime horarioAceito) {
		this.horarioAceito = horarioAceito;
	}

	public LocalDateTime getHorarioEntregue() {
		return horarioEntregue;
	}

	public void setHorarioEntregue(LocalDateTime horarioEntregue) {
		this.horarioEntregue = horarioEntregue;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public List<MensagemPedido> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemPedido> mensagens) {
		this.mensagens = mensagens;
	}

	public enum StatusPedido {
		SOLICITADO, RECEBIDO, EM_PREPARO, RECUSADO, CANCELADO, EM_ENTREGA, FINALIZADO
	}
}
