package com.lanchonete.maida.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void atualizar(@RequestBody Usuario usuario) throws ConstraintViolationException {
		dao.atualizar(usuario);
	}

	@GetMapping(value = "/{id}")
	public Response<Optional<Usuario>> buscarPorId(@PathVariable int id) {
		return Response.of(dao.buscarPorId(id));
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public List<Usuario> listar() {
		return dao.listar();
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable int id) {
		dao.deletar(id);
	}

}
