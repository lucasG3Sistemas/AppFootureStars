package com.lucasloose.appfooturestars.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.dto.HistoricoContratacaoNewDTO;
import com.lucasloose.appfooturestars.repositories.HistoricoContratacaoRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class HistoricoContratacaoService {

	@Autowired
	private HistoricoContratacaoRepository historicoContratacaoRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.empresario.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;
	
	
	public List<HistoricoContratacao> findAll() {
		List<HistoricoContratacao> historicoContratacao = historicoContratacaoRepository.findAll();
		if (historicoContratacao == null) {
			throw new ObjectNotFoundException(
					"Histórico de Contratação não encontrado!" + ", Tipo: " + HistoricoContratacao.class.getName());
		}
		return historicoContratacao;
	}

	public HistoricoContratacao find(Integer id) {
		HistoricoContratacao historicoContratacao = historicoContratacaoRepository.findOne(id);
		if (historicoContratacao == null) {
			throw new ObjectNotFoundException("Histórico de Contratação não encontrado! ID: " + id + ", Tipo: "
					+ HistoricoContratacao.class.getName());
		}
		return historicoContratacao;
	}

	public HistoricoContratacao fromDTO(HistoricoContratacaoNewDTO historicoContratacaoNewDTO) {
		Jogador jogador = new Jogador(historicoContratacaoNewDTO.getIdJogador(), "");
		ClubeFutebol clubeFutebol = new ClubeFutebol(historicoContratacaoNewDTO.getIdClubeFutebol());
		HistoricoContratacao historicoContratacao = new HistoricoContratacao(null, jogador, clubeFutebol, new Date(),
				historicoContratacaoNewDTO.getMsg_clube(), historicoContratacaoNewDTO.getMsg_jogador(),
				historicoContratacaoNewDTO.getComplemento());
		return historicoContratacao;
	}

	@Transactional
	public HistoricoContratacao insert(HistoricoContratacao historicoContratacao) {
		historicoContratacao.setId(null);
		historicoContratacao.setData_contratacao(new Date());
		return historicoContratacaoRepository.save(historicoContratacao);
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
//		String fileName = prefix + user.getId() + ".jpg";
		String fileName = prefix + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}
