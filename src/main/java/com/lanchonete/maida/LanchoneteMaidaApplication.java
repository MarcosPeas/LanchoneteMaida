package com.lanchonete.maida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.lanchonete.maida.model.Produto;
import com.lanchonete.maida.service.IProdutoService;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class LanchoneteMaidaApplication {

	@Autowired
	private IProdutoService dao;

	public static void main(String[] args) {
		SpringApplication.run(LanchoneteMaidaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				List<Produto> list = dao.clienteListar();
				System.out.println(list.size() + "\n");
				list.forEach(p -> {
					System.out.println(p);
				});
			}
		};
	}

}
