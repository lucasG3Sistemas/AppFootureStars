package com.lucasloose.appfooturestars.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.lucasloose.appfooturestars.domain.ListaObservacao;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(ListaObservacao listaObservacao) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromListaObservacao(listaObservacao);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromListaObservacao(ListaObservacao listaObservacao) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(listaObservacao.getJogadores().get(listaObservacao.getJogadores().size()-1).getEmail());
		sm.setFrom(sender);
		sm.setSubject("Parabéns! Você Está na Lista de Observação do Clube " + listaObservacao.getClubeFutebol().getNome());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(listaObservacao.toString());
		return sm;
	}
	
}
