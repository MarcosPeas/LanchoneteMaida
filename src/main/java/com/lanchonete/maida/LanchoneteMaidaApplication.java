package com.lanchonete.maida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class LanchoneteMaidaApplication {

	@Autowired
	private UsuarioDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(LanchoneteMaidaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				System.out.println(dao.buscarPorEmail("peas.shadow@gmail.com"));
				//$2a$10$XobjsdkZkO/iZc//M0Wpq.uO/WwyH2pUpLeUj3YLS923z/rdvM3kG
				//System.out.println(SenhaUtil.validarSenha("123456", "$2a$10$XobjsdkZkO/iZc//M0Wpq.uO/WwyH2pUpLeUj3YLS923z/rdvM3kG"));
			}
		};
	}

}
