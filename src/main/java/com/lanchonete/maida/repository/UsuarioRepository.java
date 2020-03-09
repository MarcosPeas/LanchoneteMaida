package com.lanchonete.maida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.maida.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByPerfil(Usuario.Perfil perfil);
}
