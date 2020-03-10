package com.lanchonete.maida.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.exceptions.ResourceNotFoundException;
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
		return rep.saveAndFlush(produto);
	}

	@Override
	public Produto buscarPorId(int id) {
		Optional<Produto> optional = rep.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("produto não encontrado");
		}
		return optional.get();
	}

	@Override
	public List<Produto> buscarPorIds(List<Integer> ids) {
		return rep.findByIdIn(ids);
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
	public List<Produto> listarPorIds(List<Integer> ids) {
		return rep.findByIdIn(ids);
	}

	@Override
	public List<Produto> listarPorCategoria(Produto.ProdutoTipo tipo) {
		return rep.findBytipoOrderByTitulo(tipo);
	}

	@Override
	public void deletar(int id) {
		rep.delete(buscarPorId(id));
	}

	@Override
	public Produto clienteBuscarPorId(int id) {
		Optional<Produto> optional = rep.findByIdAndDisponivelTrue(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("produto não encontrado");
		}
		return optional.get();
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
