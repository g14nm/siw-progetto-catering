package it.uniroma3.siw.siwprogettocatering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwprogettocatering.model.Chef;
import it.uniroma3.siw.siwprogettocatering.service.ChefService;

@Component
public class ChefValidator implements Validator {

	@Autowired
	private ChefService chefService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Chef chef = (Chef)target;
		
		if(this.chefService.existsByNomeAndCognome(chef.getNome().trim(), chef.getCognome().trim()))
			errors.rejectValue("nome", "duplicate");
	}

}