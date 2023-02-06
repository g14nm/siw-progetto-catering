package it.uniroma3.siw.siwprogettocatering.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwprogettocatering.controller.validator.IngredienteValidator;
import it.uniroma3.siw.siwprogettocatering.model.Ingrediente;
import it.uniroma3.siw.siwprogettocatering.service.IngredienteService;

@Controller
public class AdminIngredienteController {
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/admin/ingredienti")
	public String getIngredientes(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "ingredienti/adminIngredienti";
	}
	
	@GetMapping("/admin/ingredienteForm")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienti/ingredienteForm";
	}
	
	@PostMapping("/admin/ingrediente")
	public String saveIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.save(ingrediente);
			return "redirect:/admin/ingredienti";
		}
		return "ingredienti/ingredienteForm";
	}
	
	@GetMapping("/admin/edit/ingrediente/{id}")
	public String getEditIngredienteForm(@PathVariable Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingredienti/editIngredienteForm";
	}
	
	@PostMapping("/admin/edit/ingrediente/{id}")
	public String editIngrediente(@PathVariable Long id,@Valid @ModelAttribute("ingrediente") Ingrediente newIngrediente, BindingResult bindingResult) {
		Ingrediente ingrediente = this.ingredienteService.findById(id);
		if(!ingrediente.equals(newIngrediente))
			this.ingredienteValidator.validate(newIngrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			ingrediente.setNome(newIngrediente.getNome());
			ingrediente.setOrigine(newIngrediente.getOrigine());
			ingrediente.setDescrizione(newIngrediente.getDescrizione());
			this.ingredienteService.save(ingrediente);
			return "redirect:/admin/ingredienti";
		}
		return "ingredienti/editIngredienteForm";
	}
	
	@GetMapping("/admin/delete/ingrediente/{id}")
	public String getDeleteIngredienteConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.findById(id));
		return "ingredienti/deleteIngredienteConfirm";
	}
	
	@PostMapping("/admin/delete/ingrediente/{id}")
	public String deleteIngrediente(@PathVariable Long id) {
		this.ingredienteService.deleteById(id);
		return "redirect:/admin/ingredienti";
	}
	
}