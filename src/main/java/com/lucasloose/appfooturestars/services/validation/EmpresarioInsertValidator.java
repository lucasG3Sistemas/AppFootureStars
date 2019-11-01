package com.lucasloose.appfooturestars.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.dto.EmpresarioNewDTO;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.resources.exceptions.FieldMessage;

public class EmpresarioInsertValidator implements ConstraintValidator<EmpresarioInsert, EmpresarioNewDTO> {

	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	@Override
	public void initialize(EmpresarioInsert ann) {
	}

	@Override
	public boolean isValid(EmpresarioNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
				
//		Empresario aux = empresarioRepository.findByEmail(objDto.getEmail());
//		if (aux != null) {
//			list.add(new FieldMessage("email", "Email já existente"));
//		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}