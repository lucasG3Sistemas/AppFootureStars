package com.lucasloose.appfooturestars.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.dto.JogadorNewDTO;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.resources.exceptions.FieldMessage;

public class JogadorInsertValidator implements ConstraintValidator<JogadorInsert, JogadorNewDTO> {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Override
	public void initialize(JogadorInsert ann) {
	}

	@Override
	public boolean isValid(JogadorNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
				
		Jogador aux = jogadorRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}