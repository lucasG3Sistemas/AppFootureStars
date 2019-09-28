package com.lucasloose.appfooturestars.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucasloose.appfooturestars.domain.ListaObservacao;

public interface EmailService {
	
	void sendOrderConfirmationEmail(ListaObservacao listaObservacao);
	
	void sendEmail(SimpleMailMessage msg);
	
}
