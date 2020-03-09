package com.lanchonete.maida.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.exceptions.ConstraintViolationImpl;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.model.Usuario.Perfil;
import com.lanchonete.maida.repository.UsuarioRepository;
import com.lanchonete.maida.service.IUsuarioService;

@Service
public class UsuarioDao implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Optional<Usuario> buscarPorId(int id) {
		return repository.findById(id);
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
		repository.deleteById(id);
	}

	@Override
	public Usuario atualizar(Usuario usuario) {
		Optional<Usuario> optional = repository.findByPerfil(Perfil.ROLE_GESTOR);
		if (optional.isPresent()) {
			Usuario gestor = optional.get();
			if (gestor.getId() != usuario.getId() && usuario.getPerfil() == Perfil.ROLE_GESTOR) {
				String m = "Erro ao atualizar usuário";
				String mT = "Não é possível alterar para gestor";
				throw ConstraintViolationImpl.of(m, mT, usuario).getVioletaionException();
			}
		}
		return repository.saveAndFlush(usuario);
	}

}
