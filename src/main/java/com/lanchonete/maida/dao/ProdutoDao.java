package com.lanchonete.maida.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.model.Produto;
import com.lanchonete.maida.model.Produto.ProdutoTipo;
import com.lanchonete.maida.repository.ProdutoRepository;
import com.lanchonete.maida.service.IProdutoService;

@Service
public class ProdutoDao implements IProdutoService {

	@Autowired
	private ProdutoRepository rep;

	@Override
	public Produto salvar(Produto produto) {
		return rep.save(produto);
	}

	@Override
	public Optional<Produto> buscarPorId(int id) {
		return rep.findById(id);
	}

	@Override
	public List<Produto> buscarPorParteNome(String parteNome) {
		return rep.findByTituloContainingOrderByTitulo(parteNome);
	}

	@Override
	public List<Produto> listar() {
		return rep.findByOrderByTitulo();
	}

	@Override
	public List<Produto> listarPorCategoria(Produto.ProdutoTipo tipo) {
		return rep.findBytipoOrderByTitulo(tipo);
	}

	@Override
	public void deletar(int id) {
		rep.deleteById(id);
	}

	@Override
	public Optional<Produto> clienteBuscarPorId(int id) {
		return rep.findByIdAndDisponivelTrue(id);
	}

	@Override
	public List<Produto> clienteBuscarPorParteNome(String parteNome) {
		return rep.findByTituloContainingAndDisponivelTrueOrderByTitulo(parteNome);
	}

	@Override
	public List<Produto> clienteListar() {
		return rep.findByDisponivelTrueOrderByTitulo();
	}

	@Override
	public List<Produto> clienteListarPorCategoria(ProdutoTipo tipo) {
		return rep.findBytipoAndDisponivelTrueOrderByTitulo(tipo);
	}

}
