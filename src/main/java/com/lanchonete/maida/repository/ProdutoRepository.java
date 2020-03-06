package com.lanchonete.maida.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.maida.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByTituloContainingOrderByTitulo(String nomeParte);

	List<Produto> findBytipoOrderByTitulo(Produto.ProdutoTipo tipo);

	List<Produto> findByOrderByTitulo();

	Optional<Produto> findByIdAndDisponivelTrue(Integer id);

	List<Produto> findByTituloContainingAndDisponivelTrueOrderByTitulo(String nomeParte);

	List<Produto> findBytipoAndDisponivelTrueOrderByTitulo(Produto.ProdutoTipo tipo);

	List<Produto> findByDisponivelTrueOrderByTitulo();
}
