package com.lucasloose.appfooturestars.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.dto.UsuarioNewDTO;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;
import com.lucasloose.appfooturestars.resources.exceptions.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
				
		Usuario aux = usuarioRepository.findByLogin(objDto.getLogin());
		if (aux != null) {
			list.add(new FieldMessage("login", "Login já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}