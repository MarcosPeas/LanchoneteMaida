package com.lanchonete.maida.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.service.IUsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = usuarioService.buscarPorEmail(username);

		if (optional.isPresent()) {
			return new JwtUsuario(optional.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}