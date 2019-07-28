package com.lucasloose.appfooturestars;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;

@SpringBootApplication
public class AppFootureStarsApplication implements CommandLineRunner {

	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AppFootureStarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Modalidade mod1 = new Modalidade(1, "Futebol de Campo");
		Modalidade mod2 = new Modalidade(2, "Futebol de Salão/Futsal");
		
		modalidadeRepository.save(Arrays.asList(mod1, mod2));
		
	}

}
