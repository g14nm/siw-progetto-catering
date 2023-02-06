package it.uniroma3.siw.siwprogettocatering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Component
public class PiattoValidator implements Validator {

	@Autowired
	private PiattoService piattoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Piatto piatto = (Piatto)target;
		
		if(this.piattoService.existsByNome(piatto.getNome()))
			errors.rejectValue("nome", "duplicate");
	}

}