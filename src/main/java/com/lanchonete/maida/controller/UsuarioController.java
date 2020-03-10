package com.lanchonete.maida.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) throws ConstraintViolationException {
		dao.atualizar(usuario);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Usuario>> buscarPorId(@PathVariable int id) {
		return ResponseEntity.ok(Response.of(dao.buscarPorId(id)));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Response<List<Usuario>>> listar() {
		return ResponseEntity.ok(Response.of(dao.listar()));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable int id) {
		dao.deletar(id);
		return ResponseEntity.ok().build();
	}

}
