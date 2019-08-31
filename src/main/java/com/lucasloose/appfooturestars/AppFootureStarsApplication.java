package com.lucasloose.appfooturestars;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.repositories.HistoricoContratacaoRepository;
import com.lucasloose.appfooturestars.repositories.JogadorLanceRepository;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ListaObservacaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;

@SpringBootApplication
public class AppFootureStarsApplication implements CommandLineRunner {

	@Autowired
	private ModalidadeRepository modalidadeRepository;

	@Autowired
	private ModalidadePosicaoRepository modalidadePosicaoRepository;

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private HistoricoContratacaoRepository historicoContratacaoRepository;
	
	@Autowired
	private JogadorLanceRepository jogadorLanceRepository;
	
	@Autowired
	private ListaObservacaoRepository listaObservacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppFootureStarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Modalidade mod1 = new Modalidade(1, "Futebol de Campo");
		Modalidade mod2 = new Modalidade(2, "Futebol de Salão/Futsal");
		Modalidade mod3 = new Modalidade(3, "Futebol");
		Modalidade mod4 = new Modalidade(4, "Futebol XXXX");
		Modalidade mod5 = new Modalidade(5, "Futebol AAAA");
		Modalidade mod6 = new Modalidade(6, "Futebol ZZZZ");
		Modalidade mod7 = new Modalidade(7, "Futebol PPPP");
		Modalidade mod8 = new Modalidade(8, "Futebol LKLLKL");
		Modalidade mod9 = new Modalidade(9, "Futebol SSSSS");
		Modalidade mod10 = new Modalidade(10, "Futebol LLLLLL");
		
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
		
		ClubeFutebol clube1 = new ClubeFutebol(null, "Gremio", 1, "12312312", "Brasil", "RS", "Rio Grande do Sul", "gremio@gg", "GREMIOOO");
		ClubeFutebol clube2 = new ClubeFutebol(null, "Inter", 1, "19092018", "Brasil", "RS", "Rio Grande do Sul", "inti@gg", "INTI");
		
//		mod1.getModalidadeClubes().addAll(Arrays.asList(clube1, clube2));
//		mod2.getModalidadeClubes().addAll(Arrays.asList(clube1));
		
		modalidadeRepository.save(Arrays.asList(mod1, mod2, mod3, mod4, mod5, mod6, mod7, mod8, mod9, mod10));
		modalidadePosicaoRepository.save(Arrays.asList(modPos1, modPos2, modPos3, modPos4, modPos5, modPos6, modPos7, modPos8, modPos9, modPos10));
		
		clube1.getModalidades().addAll(Arrays.asList(mod1, mod2));
		clube2.getModalidades().addAll(Arrays.asList(mod1));
		clubeFutebolRepository.save(Arrays.asList(clube1));
		clubeFutebolRepository.save(Arrays.asList(clube2));
		
		Jogador jogador1 = new Jogador(null, "JOGADOR DANI");
		Jogador jogador2 = new Jogador(null, "JOGADOR LUCAS");
		Jogador jogador3 = new Jogador(null, "JOGADOR XXXXXXX");
		
//		mod1.getModalidadeJogadores().addAll(Arrays.asList(jogador1, jogador2, jogador3));
//		mod2.getModalidadeJogadores().addAll(Arrays.asList(jogador1));
//		
//		modPos1.getjogadores().addAll(Arrays.asList(jogador1, jogador2, jogador3));
//		modPos2.getjogadores().addAll(Arrays.asList(jogador1));
		
		jogador1.getModalidades().addAll(Arrays.asList(mod1, mod2));
		jogador2.getModalidades().addAll(Arrays.asList(mod1));
		jogador3.getModalidades().addAll(Arrays.asList(mod1));
		jogador1.getModalidadesPosicoes().addAll(Arrays.asList(modPos1, modPos2));
		jogador2.getModalidadesPosicoes().addAll(Arrays.asList(modPos1));
		jogador3.getModalidadesPosicoes().addAll(Arrays.asList(modPos1));
		jogadorRepository.save(Arrays.asList(jogador1, jogador2, jogador3));
		
		Empresario emp1 = new Empresario(null, "Chico");
		empresarioRepository.save(Arrays.asList(emp1));
		
		HistoricoContratacao hisContr1 = new HistoricoContratacao(null, jogador1, clube1, "MUITO FELIZ", "AGRADEÇO", "");
		HistoricoContratacao hisContr2 = new HistoricoContratacao(null, jogador1, clube1, "A DANI É SARNA", "AMO A DANI", "");
		historicoContratacaoRepository.save(Arrays.asList(hisContr1, hisContr2));
		
		Date data = new Date();
		JogadorLance lance1 = new JogadorLance(null, data, "Brasil", "SC", "Palmitos", jogador1);
		JogadorLance lance2 = new JogadorLance(null, data, "Brasil", "MG", "Belo Horizonte", jogador1);
		JogadorLance lance3 = new JogadorLance(null, data, "Brasil", "RJ", "Rio de Janeiro", jogador2);
		JogadorLance lance4 = new JogadorLance(null, data, "Brasil", "SP", "São Paulo", jogador3);
		jogadorLanceRepository.save(Arrays.asList(lance1, lance2, lance3, lance4));
		
		ListaObservacao lista1 = new ListaObservacao(null, clube1, null);
		ListaObservacao lista2 = new ListaObservacao(null, null, emp1);
		lista1.getJogadores().addAll(Arrays.asList(jogador1, jogador2));
		lista2.getJogadores().addAll(Arrays.asList(jogador2, jogador3));
		listaObservacaoRepository.save(Arrays.asList(lista1, lista2));
		
//		Usuario usu1 = new Usuario("lucas", "123", "LUCAS", 1);
//		usuarioRepository.save(Arrays.asList(usu1));
	}

}
