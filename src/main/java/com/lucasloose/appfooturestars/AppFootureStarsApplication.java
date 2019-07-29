package com.lucasloose.appfooturestars;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;

@SpringBootApplication
public class AppFootureStarsApplication implements CommandLineRunner {

	@Autowired
	private ModalidadeRepository modalidadeRepository;

	@Autowired
	private ModalidadePosicaoRepository modalidadePosicaoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppFootureStarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Modalidade mod1 = new Modalidade(1, "Futebol de Campo");
		Modalidade mod2 = new Modalidade(2, "Futebol de Salão/Futsal");
		
		ModalidadePosicao modPos1 = new ModalidadePosicao(null, "Goleiro");
		ModalidadePosicao modPos2 = new ModalidadePosicao(null, "Zagueiro");
		ModalidadePosicao modPos3 = new ModalidadePosicao(null, "Lateral");
		ModalidadePosicao modPos4 = new ModalidadePosicao(null, "Volante");
		ModalidadePosicao modPos5 = new ModalidadePosicao(null, "Meio-Campo");
		ModalidadePosicao modPos6 = new ModalidadePosicao(null, "Atacante de Lado de Campo");
		ModalidadePosicao modPos7 = new ModalidadePosicao(null, "Centro-Avante");
		ModalidadePosicao modPos8 = new ModalidadePosicao(null, "Fixo");
		ModalidadePosicao modPos9 = new ModalidadePosicao(null, "Ala");
		ModalidadePosicao modPos10 = new ModalidadePosicao(null, "Pivô");
		
		mod1.getModalidadePosicoes().addAll(Arrays.asList(modPos1, modPos2, modPos3, modPos4, modPos5, modPos6, modPos7));
		mod2.getModalidadePosicoes().addAll(Arrays.asList(modPos1, modPos8, modPos9, modPos10));
		
		modPos1.getModalidades().addAll(Arrays.asList(mod1, mod2));
		modPos2.getModalidades().addAll(Arrays.asList(mod1));
		modPos3.getModalidades().addAll(Arrays.asList(mod1));
		modPos4.getModalidades().addAll(Arrays.asList(mod1));
		modPos5.getModalidades().addAll(Arrays.asList(mod1));
		modPos6.getModalidades().addAll(Arrays.asList(mod1));
		modPos7.getModalidades().addAll(Arrays.asList(mod1));
		modPos8.getModalidades().addAll(Arrays.asList(mod2));
		modPos9.getModalidades().addAll(Arrays.asList(mod2));
		modPos10.getModalidades().addAll(Arrays.asList(mod2));
		
		modalidadeRepository.save(Arrays.asList(mod1, mod2));
		modalidadePosicaoRepository.save(Arrays.asList(modPos1, modPos2, modPos3, modPos4, modPos5, modPos6, modPos7, modPos8, modPos9, modPos10));
	}

}
