package com.lanchonete.maida.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.exceptions.ConstraintViolationImpl;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.model.UsuarioToken;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.security.JwtTokenUtil;
import com.lanchonete.maida.security.JwtUsuario;
import com.lanchonete.maida.security.TokenDto;
import com.lanchonete.maida.service.IUsuarioService;
import com.lanchonete.maida.util.SenhaUtil;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUsuarioService service;

	@PostMapping(value = "/cadastro")
	public ResponseEntity<Response<UsuarioToken>> cadastrar(@RequestBody Usuario usuario) {

		if (usuario.getPerfil() == Usuario.Perfil.ROLE_GESTOR) {
			String m = "Erro ao cadastrar usuário";
			String mT = "Não é possível cadastrar gestores";
			throw ConstraintViolationImpl.of(m, mT, usuario).getViolationException();
		}
		if(usuario.getSenha() == null) {
			String m = "Erro ao cadastrar usuário";
			String mT = "Informe a senha";
			throw ConstraintViolationImpl.of(m, mT, usuario).getViolationException();
		}
		if(usuario.getSenha().length() < 6 || usuario.getSenha().length() > 16) {
			String m = "Erro ao cadastrar usuário";
			String mT = "A senha deve conter de 6 a 16 caracteres";
			throw ConstraintViolationImpl.of(m, mT, usuario).getViolationException();
		}

		service.salvar(usuario);
		JwtUsuario jwtUsuario = new JwtUsuario(usuario);
		String token = jwtTokenUtil.obterToken(jwtUsuario);
		Response<UsuarioToken> response = Response.of(new UsuarioToken(token, usuario));
		return new ResponseEntity<Response<UsuarioToken>>(response, HttpStatus.CREATED);
	}

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<UsuarioToken>> login(@RequestBody Usuario usuario) throws AuthenticationException {

		Usuario u = service.buscarPorEmail(usuario.getEmail());

		String senhaEncrip = u.getSenha();
		if (!SenhaUtil.validarSenha(usuario.getSenha(), senhaEncrip)) {
			throw new AuthenticationCredentialsNotFoundException("Senha incorreta");
		}

		log.info("Gerando token para o email {}.", usuario.getEmail());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);

		Response<UsuarioToken> response = Response.of(new UsuarioToken(token, u));

		return ResponseEntity.ok(response);
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<TokenDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		Response<TokenDto> response = Response.erros();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		if (!token.isPresent()) {
			response.getErros().add("Token não informado.");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			response.getErros().add("Token inválido ou expirado.");
		}

		if (!response.getErros().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		response.setData(new TokenDto(refreshedToken));

		return ResponseEntity.ok(response);
	}

}
