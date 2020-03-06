package com.lanchonete.maida.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.Produto;
import com.lanchonete.maida.model.Produto.ProdutoTipo;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IProdutoService;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

	@Autowired
	private IProdutoService dao;

	// PARA GESTOR

	@PostMapping
	public ResponseEntity<Response<Integer>> salvar(@RequestBody Produto produto) {
		dao.salvar(produto);
		Response<Integer> response = Response.of(produto.getId());
		return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public void atualizar(Produto produto) {
		dao.salvar(produto);
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable int id) {
		dao.deletar(id);
	}

	@GetMapping(value = "/gestor/{id}")
	public Response<Optional<Produto>> buscarPorId(@PathVariable int id) {
		return Response.of(dao.buscarPorId(id));
	}

	@GetMapping(value = "/gestor/like/{nome}")
	public Response<List<Produto>> buscarPorParteNome(@PathVariable String nome) {
		return Response.of(dao.buscarPorParteNome(nome));
	}

	// PARA CLIENTES

	@GetMapping(value = "/like/{nome}")
	public Response<List<Produto>> clienteBuscarPorParteNome(@PathVariable String nome) {
		return Response.of(dao.clienteBuscarPorParteNome(nome));
	}

	@GetMapping(value = "/{id}")
	public Response<Optional<Produto>> clienteBuscarPorId(@PathVariable int id) {
		return Response.of(dao.clienteBuscarPorId(id));
	}

	@GetMapping(value = "/categoria/{tipo}")
	public ResponseEntity<Response<Object>> clienteBuscarPorTipo(@PathVariable String tipo) {
		try {
			return ResponseEntity.ok(Response.of(dao.clienteListarPorCategoria(ProdutoTipo.valueOf(tipo))));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Response.erro("Categoria inválida"));
		}
	}

}
