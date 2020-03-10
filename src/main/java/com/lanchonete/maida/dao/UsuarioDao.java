package com.lanchonete.maida.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.exceptions.ConstraintViolationImpl;
import com.lanchonete.maida.exceptions.ResourceNotFoundException;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.model.Usuario.Perfil;
import com.lanchonete.maida.repository.UsuarioRepository;
import com.lanchonete.maida.service.IUsuarioService;

@Service
public class UsuarioDao implements IUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario buscarPorEmail(String email) {
		Optional<Usuario> optional = repository.findByEmail(email);
		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return optional.get();
	}

	@Override
	public Usuario buscarPorId(int id) {
		Optional<Usuario> optional = repository.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Usuário não encontrado");
		}
		return optional.get();
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return repository.saveAndFlush(usuario);
	}

	@Override
	public List<Usuario> listar() {
		return repository.findAll();
	}

	@Override
	public void deletar(int id) {
		repository.delete(buscarPorId(id));
	}

	@Override
	public Usuario atualizar(Usuario usuario) {
		buscarPorId(usuario.getId());
		Optional<Usuario> optional = repository.findByPerfil(Perfil.ROLE_GESTOR);
		if (optional.isPresent()) {
			Usuario gestor = optional.get();
			if (gestor.getId() != usuario.getId() && usuario.getPerfil() == Perfil.ROLE_GESTOR) {
				String m = "Erro ao atualizar usuário";
				String mT = "Não é possível alterar para gestor";
				throw ConstraintViolationImpl.of(m, mT, usuario).getViolationException();
			}
		}
		return repository.saveAndFlush(usuario);
	}

}
