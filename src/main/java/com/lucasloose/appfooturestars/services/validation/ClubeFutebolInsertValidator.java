package com.lucasloose.appfooturestars.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.dto.ClubeFutebolNewDTO;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.resources.exceptions.FieldMessage;

public class ClubeFutebolInsertValidator implements ConstraintValidator<ClubeFutebolInsert, ClubeFutebolNewDTO> {

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	@Override
	public void initialize(ClubeFutebolInsert ann) {
	}

	@Override
	public boolean isValid(ClubeFutebolNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
				
//		ClubeFutebol aux = clubeFutebolRepository.findByEmail(objDto.getEmail());
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