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

import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IUsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService dao;

	@PostMapping
	public ResponseEntity<Response<Integer>> salvar(@RequestBody Usuario usuario) {
		dao.salvar(usuario);
		Response<Integer> response = Response.of(usuario.getId());
		return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public void atualizar(@RequestBody Usuario usuario) {
		dao.salvar(usuario);
	}

	@GetMapping(value = "/{id}")
	public Response<Optional<Usuario>> buscarPorId(@PathVariable int id) {
		return Response.of(dao.buscarPorId(id));
	}

	@GetMapping
	public List<Usuario> listar() {
		return dao.listar();
	}

	@DeleteMapping(value = "{/id}")
	public void deletar(@PathVariable int id) {
		dao.deletar(id);
	}

}
