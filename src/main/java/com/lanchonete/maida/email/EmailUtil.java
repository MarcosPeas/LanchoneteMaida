package com.lanchonete.maida.email;

import org.springframework.mail.SimpleMailMessage;

import com.lanchonete.maida.model.Usuario;

public class EmailUtil {
	private EmailUtil() {
	}

	public static SimpleMailMessage templete(Usuario usuario, String token) {
		SimpleMailMessage message = new SimpleMailMessage();

		String texto = construirTemplete(usuario, token);

		message.setText(texto);
		message.setTo(usuario.getEmail());
		message.setFrom("peas.shadow@gmail.com");
		message.setSubject("Lanchote Maida. Recuperação de senha");
		return message;
	}

	private static String construirTemplete(Usuario usuario, String token) {
		String[] strings = usuario.getNome().split(" ");
		StringBuilder sb = new StringBuilder();
		sb.append("Olá, ").append(strings[0]).append(".\n\n").append("Para alterar sua senha, utilize o token:\n")
				.append(token).append("\n\n")
				.append("Se você não solicitou a alteração da sua senha, ignore este e-mail.\n\n\n\n")
				.append("A equipe Lanchonete Maida agradece.");
		return sb.toString();
	}
}
