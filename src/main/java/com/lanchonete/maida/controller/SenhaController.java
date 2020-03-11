package com.lanchonete.maida.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.email.EmailUtil;
import com.lanchonete.maida.email.SenhaModel;
import com.lanchonete.maida.exceptions.ConstraintViolationImpl;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.security.JwtTokenUtil;
import com.lanchonete.maida.service.IUsuarioService;
import com.lanchonete.maida.util.SenhaUtil;

@RestController
@RequestMapping("/v1/senha")
public class SenhaController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@PostMapping
	public ResponseEntity<Response<Void>> sendMail(@RequestParam String email) {
		Usuario usuario = usuarioService.buscarPorEmail(email);
		String token = tokenUtil.gerarTokenDeRecuperacaoDeSenha(email);
		SimpleMailMessage message = EmailUtil.templete(usuario, token);
		mailSender.send(message);
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<Response<Object>> alterarSenha(@RequestBody SenhaModel senhaModel) {

		if (senhaModel == null || senhaModel.getSenha1() == null || senhaModel.getSenha2() == null) {
			throw ConstraintViolationImpl.of("Erro ao trocar senha", "Informe a senha e a sua repetição", senhaModel)
					.getViolationException();
		}

		if (!senhaModel.getSenha1().equals(senhaModel.getSenha2())) {
			throw ConstraintViolationImpl
					.of("Erro ao trocar senha", "A senha e a repetição da senha não são iguais", senhaModel)
					.getViolationException();
		}
		if (tokenUtil.tokenValido(senhaModel.getToken())) {
			String email = tokenUtil.pegarEmailDoToken(senhaModel.getToken());
			Usuario usuario = usuarioService.buscarPorEmail(email);
			usuario.setSenha(SenhaUtil.encriptarSenha(senhaModel.getSenha1()));
			usuarioService.salvar(usuario);
			return ResponseEntity.ok().body(Response.of(null));
		}
		return ResponseEntity.ok().body(Response.erros("Token inválido"));
	}
}
