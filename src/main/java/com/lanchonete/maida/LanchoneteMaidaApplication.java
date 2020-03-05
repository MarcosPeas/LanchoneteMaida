package com.lanchonete.maida;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lanchonete.maida.model.Usuario;

@SpringBootApplication
public class LanchoneteMaidaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanchoneteMaidaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				System.out.println("E começou...	");
				
			}
		};
	}

}
