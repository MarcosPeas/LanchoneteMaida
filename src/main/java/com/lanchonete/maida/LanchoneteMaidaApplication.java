package com.lanchonete.maida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lanchonete.maida.security.JwtTokenUtil;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class LanchoneteMaidaApplication implements WebMvcConfigurer {
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	public static void main(String[] args) {
		SpringApplication.run(LanchoneteMaidaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		return a -> {
			/**/System.out.println("Inciou...");
			/*
			 * System.out.println("localhost:8080/swagger-ui.html");
			 * 
			 * Usuario u = new Usuario(); u.setEmail("maria.clara@gmail.com");
			 * u.setPerfil(Usuario.Perfil.ROLE_CLIENTE);
			 * 
			 * String token =
			 * jwtTokenUtil.gerarTokenDeRecuperacaoDeSenha("peas.shadow@gmail.com");
			 * 
			 * System.out.println(token);
			 */

		};
	}
//localhost:8080/swagger-ui.html
}
