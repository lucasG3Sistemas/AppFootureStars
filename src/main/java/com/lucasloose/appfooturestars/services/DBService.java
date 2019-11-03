package com.lucasloose.appfooturestars.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.domain.enums.TipoUsuario;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.repositories.HistoricoContratacaoRepository;
import com.lucasloose.appfooturestars.repositories.JogadorLanceRepository;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ListaObservacaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	
	
	public void instantiateTestDatabase() {
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
		
		mod1.getPosicoes().addAll(Arrays.asList(modPos1, modPos2, modPos3, modPos4, modPos5, modPos6, modPos7));
		mod2.getPosicoes().addAll(Arrays.asList(modPos1, modPos8, modPos9, modPos10));
		
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
		
		Usuario usuJogador = new Usuario("lucasloose97@gmail.com", bCryptPasswordEncoder.encode("123"), TipoUsuario.JOGADORFUTEBOL);
		usuJogador.addPerfil(Perfil.ADMIN);
		Usuario usuEmpr = new Usuario("empresario@gmail.com", bCryptPasswordEncoder.encode("123"), TipoUsuario.EMPRESARIO);
		usuEmpr.addPerfil(Perfil.ADMIN);
		Usuario usuClub1 = new Usuario("clube1@gmail.com", bCryptPasswordEncoder.encode("123"), TipoUsuario.CLUBEFUTEBOL);
		usuClub1.addPerfil(Perfil.ADMIN);
		Usuario usuClub2 = new Usuario("clube2@gmail.com", bCryptPasswordEncoder.encode("123"), TipoUsuario.CLUBEFUTEBOL);
		usuClub2.addPerfil(Perfil.ADMIN);
		
		usuarioRepository.save(Arrays.asList(usuJogador, usuEmpr, usuClub1, usuClub2));
		
		ClubeFutebol clube1 = new ClubeFutebol(null, "Gremio", 1, "12312312", "Brasil", "SC", "Palmitos", "GREMIOOO", usuClub1);
		ClubeFutebol clube2 = new ClubeFutebol(null, "Inter", 2, "19092018", "Brasil", "SC", "Chapecó", "INTer", usuClub2);
		
//		mod1.getModalidadeClubes().addAll(Arrays.asList(clube1, clube2));
//		mod2.getModalidadeClubes().addAll(Arrays.asList(clube1));
		
		modalidadeRepository.save(Arrays.asList(mod1, mod2, mod3, mod4, mod5, mod6, mod7, mod8, mod9, mod10));
		modalidadePosicaoRepository.save(Arrays.asList(modPos1, modPos2, modPos3, modPos4, modPos5, modPos6, modPos7, modPos8, modPos9, modPos10));
		
		clube1.setUsuario(usuClub1);
		clube2.setUsuario(usuClub2);
		
		clube1.getModalidades().addAll(Arrays.asList(mod1, mod2));
		clube2.getModalidades().addAll(Arrays.asList(mod1));
		clubeFutebolRepository.save(Arrays.asList(clube1));
		clubeFutebolRepository.save(Arrays.asList(clube2));
		
		Jogador jogador1 = new Jogador(null, "Pedro da Silva", "83633100008", new Date(), "Brasil",
				"SC", "Palmitos", 1, 183.0, 78.0, 1, "23212360", mod1, 1, 55, 49, 988563215, usuJogador.getEmail(), "");
		Jogador jogador2 = new Jogador(null, "Lucas da Silva", "62645237045", new Date(), "Brasil",
				"SC", "Pinhalzinho", 1, 179.0, 72.0, 2, "", mod1, 1, 55, 49, 988501200, "lucas@gmail.com", "");
		Jogador jogador3 = new Jogador(null, "Daniela Silveira", "19112208027", new Date(), "Brasil",
				"SC", "Chapecó", 1, 167.0, 59.0, 1, "23212360", mod1, 1, 55, 49, 999365698, "footure.stars@gmail.com", "");
		Jogador jogador4 = new Jogador(null, "Chico Bernardo", "98865798630", new Date(), "Brasil",
				"SC", "Chapecó", 1, 167.0, 70.0, 2, "", mod1, 1, 55, 49, 999365698, "lucas.loose@unochapeco.edu.br", "");
		jogador1.setUsuario(usuJogador);
//		mod1.getModalidadeJogadores().addAll(Arrays.asList(jogador1, jogador2, jogador3));
//		mod2.getModalidadeJogadores().addAll(Arrays.asList(jogador1));
//		
//		modPos1.getjogadores().addAll(Arrays.asList(jogador1, jogador2, jogador3));
//		modPos2.getjogadores().addAll(Arrays.asList(jogador1));
		
//		jogador1.getModalidades().add(mod1);
//		jogador1.getModalidades().addAll(Arrays.asList(mod1, mod2));
//		jogador2.getModalidades().addAll(Arrays.asList(mod1));
//		jogador3.getModalidades().addAll(Arrays.asList(mod1));
		//jogador1.setModalidade(mod1);
//		jogador2.setModalidade(mod1);
//		jogador3.setModalidade(mod3);
		jogador1.getPosicoes().addAll(Arrays.asList(modPos5, modPos6));
		jogador2.getPosicoes().addAll(Arrays.asList(modPos1));
		jogador3.getPosicoes().addAll(Arrays.asList(modPos4));
		jogador4.getPosicoes().addAll(Arrays.asList(modPos4));
		
		Empresario emp1 = new Empresario(null, "Chico");
		emp1.setUsuario(usuEmpr);
		empresarioRepository.save(Arrays.asList(emp1));
		
		jogador2.setEmpresario(emp1);
		jogador3.setClubeFutebol(clube1);
		jogadorRepository.save(Arrays.asList(jogador1, jogador2, jogador3, jogador4));
		
		HistoricoContratacao hisContr1 = new HistoricoContratacao(null, jogador1, clube1, new Date(),"É um grande jogador e estamos muitos felizes de podermos contar com ele", "É um prazer enorme em poder vestir essa camisa, espero trazer muitas alegrias para a torcida, com certeza serei muito feliz aqui!", "");
		HistoricoContratacao hisContr2 = new HistoricoContratacao(null, jogador2, clube2, new Date(), "Felizes", "Feliz com a oportunidade", "");
		historicoContratacaoRepository.save(Arrays.asList(hisContr1, hisContr2));
		
		Date data = new Date();
		JogadorLance lance1 = new JogadorLance(null, "Gols no Campeonato Estadual", "https://www.youtube.com/embed/ALXkA9OxGpw", "Gols no campeonato Estadual de Futebol realizado entre os dias 05 à 12.", data, "", jogador1);
		JogadorLance lance2 = new JogadorLance(null, "Gols no Campeonato Brasileiro", "https://www.youtube.com/embed/is3Tr3fZCOI", "Gols no campeonato brasileiro de futebol de campo.", data, "", jogador2);
		JogadorLance lance3 = new JogadorLance(null, "Gols de Falta", "https://www.youtube.com/embed/ALXkA9OxGpw", "Gols de falta no ano de 2018.", data, "", jogador2);
//		JogadorLance lance3 = new JogadorLance(null, data, "Brasil", "RJ", "Rio de Janeiro", jogador2);
//		JogadorLance lance4 = new JogadorLance(null, data, "Brasil", "SP", "São Paulo", jogador3);
		jogadorLanceRepository.save(Arrays.asList(lance1, lance2, lance3));
		
		ListaObservacao lista1 = new ListaObservacao(null, clube1, null);
		ListaObservacao lista2 = new ListaObservacao(null, null, emp1);
		lista1.getJogadores().addAll(Arrays.asList(jogador1, jogador2));
		lista2.getJogadores().addAll(Arrays.asList(jogador3));
		listaObservacaoRepository.save(Arrays.asList(lista1, lista2));
	}
	
}
