package com.lanchonete.maida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.Produto;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IProdutoService;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

	@Autowired
	private IProdutoService dao;

	// PARA GESTOR

	@PostMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Response<Integer>> salvar(@RequestBody Produto produto) {
		dao.salvar(produto);
		Response<Integer> response = Response.of(produto.getId());
		return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public void atualizar(@RequestBody Produto produto) {
		dao.salvar(produto);
		ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public void deletar(@PathVariable int id) {
		dao.deletar(id);
	}

	@GetMapping(value = "/gestor/{id}")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public Response<Produto> buscarPorId(@PathVariable int id) {
		Produto produto = dao.buscarPorId(id);
		return Response.of(produto);
	}

	@GetMapping(value = "/gestor/like/{nome}")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public Response<List<Produto>> buscarPorParteNome(@PathVariable String nome) {
		return Response.of(dao.buscarPorParteNome(nome));
	}

	@GetMapping(value = "/gestor/categoria/{tipo}")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Response<Object>> buscarPorTipo(@PathVariable Produto.ProdutoTipo tipo) {
		return ResponseEntity.ok(Response.of(dao.listarPorCategoria(tipo)));
	}
	
	
	// 			PARA CLIENTES

	@GetMapping(value = "/like/{nome}")
	public Response<List<Produto>> clienteBuscarPorParteNome(@PathVariable String nome) {
		return Response.of(dao.clienteBuscarPorParteNome(nome));
	}

	@GetMapping(value = "/{id}")
	public Response<Produto> clienteBuscarPorId(@PathVariable int id) {
		Produto produto = dao.clienteBuscarPorId(id);
		return Response.of(produto);
	}

	@GetMapping(value = "/categoria/{tipo}")
	public ResponseEntity<Response<Object>> clienteBuscarPorTipo(@PathVariable Produto.ProdutoTipo tipo) {
		return ResponseEntity.ok(Response.of(dao.clienteListarPorCategoria(tipo)));
	}
	
}
