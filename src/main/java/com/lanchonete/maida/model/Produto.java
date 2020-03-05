package com.lanchonete.maida.model;

import java.math.BigDecimal;

public class Produto {

	private int id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
	private boolean disponivel;
	private ProdutoTipo tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public ProdutoTipo getTipo() {
		return tipo;
	}

	public void setTipo(ProdutoTipo tipo) {
		this.tipo = tipo;
	}

	public enum ProdutoTipo {
		COMIDA, BEBIDA, SOBREMESA
	}
}
