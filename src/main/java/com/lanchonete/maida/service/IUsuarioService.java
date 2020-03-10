package com.lanchonete.maida.service;

import java.util.List;

import com.lanchonete.maida.model.Usuario;

public interface IUsuarioService {

	Usuario buscarPorEmail(String email);

	Usuario buscarPorId(int id);

	Usuario salvar(Usuario usuario);

	Usuario atualizar(Usuario usuario);

	List<Usuario> listar();

	void deletar(int id);
}
