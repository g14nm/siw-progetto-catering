package it.uniroma3.siw.siwprogettocatering.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocatering.service.IngredienteService;

public class AdminIngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String getIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "ingredienti/ingredienti";
	}	
	
}