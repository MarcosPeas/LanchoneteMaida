package com.lanchonete.maida.service;

import java.util.List;
import java.util.Optional;

import com.lanchonete.maida.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> buscarPorEmail(String email);

	Optional<Usuario> buscarPorId(int id);

	Usuario salvar(Usuario usuario);

	List<Usuario> listar();
	
	void deletar(int id);
}
