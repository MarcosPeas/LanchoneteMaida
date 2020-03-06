package com.lanchonete.maida.service;

import java.util.List;
import java.util.Optional;

import com.lanchonete.maida.model.Produto;

public interface IProdutoService {

	Produto salvar(Produto produto);

	Optional<Produto> buscarPorId(int id);

	List<Produto> buscarPorParteNome(String parteNome);

	List<Produto> listar();

	List<Produto> listarPorCategoria(Produto.ProdutoTipo tipo);

	Optional<Produto> clienteBuscarPorId(int id);

	List<Produto> clienteBuscarPorParteNome(String parteNome);

	List<Produto> clienteListar();

	List<Produto> clienteListarPorCategoria(Produto.ProdutoTipo tipo);

	void deletar(int id);
}
