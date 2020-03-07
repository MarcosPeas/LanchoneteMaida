package com.lanchonete.maida.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.email.EmailUtil;
import com.lanchonete.maida.email.SenhaModel;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.security.JwtTokenUtil;
import com.lanchonete.maida.service.IUsuarioService;
import com.lanchonete.maida.util.SenhaUtil;

@RestController("/senha")
public class SenhaController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@PostMapping(value = "/recuperar")
	public ResponseEntity<Response<Void>> sendMail(@RequestParam String email) {
		Optional<Usuario> optional = usuarioService.buscarPorEmail(email);

		if (optional.isEmpty()) {
			ResponseEntity<Response<Void>> responseEntity = new ResponseEntity<Response<Void>>(
					Response.erro("E-mail não encontrado"), HttpStatus.NOT_FOUND);
			return responseEntity;
		}

		try {
			String token = tokenUtil.gerarTokenDeRecuperacaoDeSenha(email);
			SimpleMailMessage message = EmailUtil.templete(optional.get(), token);
			mailSender.send(message);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			// return "Erro ao enviar email.";
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		}
	}

	@PatchMapping
	public ResponseEntity<Response<Object>> alterarSenha(@RequestBody SenhaModel senhaModel) {
		System.out.println(senhaModel);
		if (!senhaModel.getSenha1().equals(senhaModel.getSenha2())) {
			return ResponseEntity.badRequest().body(Response.erro("A senha e a repetição da senha não são iguais"));
		}
		if (tokenUtil.tokenValido(senhaModel.getToken())) {
			String email = tokenUtil.pegarEmailDoToken(senhaModel.getToken());
			System.out.println(email);
			Optional<Usuario> optional = usuarioService.buscarPorEmail(email);
			if (optional.isEmpty()) {
				Response<Object> response = Response.erro("Usuário não encontrado");
				ResponseEntity<Response<Object>> responseEntity = new ResponseEntity<Response<Object>>(response,
						HttpStatus.NOT_FOUND);
				return responseEntity;
			}
			Usuario usuario = optional.get();
			usuario.setSenha(SenhaUtil.encriptarSenha(senhaModel.getSenha1()));
			usuarioService.salvar(usuario);
			return ResponseEntity.ok().body(Response.of(null));
		}
		return ResponseEntity.ok().body(Response.erro("Token inválido"));
	}
}
