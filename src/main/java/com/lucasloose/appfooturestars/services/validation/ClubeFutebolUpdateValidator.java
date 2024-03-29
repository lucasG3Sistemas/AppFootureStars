package com.lucasloose.appfooturestars.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.dto.ClubeFutebolDTO;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.resources.exceptions.FieldMessage;

public class ClubeFutebolUpdateValidator implements ConstraintValidator<ClubeFutebolUpdate, ClubeFutebolDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	@Override
	public void initialize(ClubeFutebolUpdate ann) {
	}

	@Override
	public boolean isValid(ClubeFutebolDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
				
//		ClubeFutebol aux = clubeFutebolRepository.findByEmail(objDto.getEmail());
//		if (aux != null && !aux.getId().equals(uriId)) {
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