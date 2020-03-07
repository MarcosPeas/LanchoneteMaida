package com.lanchonete.maida;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class LanchoneteMaidaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LanchoneteMaidaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		return a -> {
			System.out.println("Inciou...");
		};
	}

}
