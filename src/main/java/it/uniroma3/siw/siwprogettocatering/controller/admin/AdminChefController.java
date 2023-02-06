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

import it.uniroma3.siw.siwprogettocatering.controller.validator.ChefValidator;
import it.uniroma3.siw.siwprogettocatering.model.Chef;
import it.uniroma3.siw.siwprogettocatering.service.ChefService;

@Controller
public class AdminChefController {
	
	@Autowired
	private ChefValidator chefValidator;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/admin/chefs")
	public String getChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefs/adminChefs";
	}
	
	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefs/chefForm";
	}
	
	@PostMapping("/admin/chef")
	public String saveChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.chefService.save(chef);
			return "redirect:/admin/chefs";
		}
		return "chefs/chefForm";
	}
	
	@GetMapping("/admin/edit/chef/{id}")
	public String getEditChefForm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefs/editChefForm";
	}
	
	@PostMapping("/admin/edit/chef/{id}")
	public String editChef(@PathVariable Long id,@Valid @ModelAttribute("chef") Chef newChef, BindingResult bindingResult) {
		Chef chef = this.chefService.findById(id);
		if(!chef.equals(newChef))
			this.chefValidator.validate(newChef, bindingResult);
		if(!bindingResult.hasErrors()) {
			chef.setNome(newChef.getNome());
			chef.setCognome(newChef.getCognome());
			chef.setNazionalita(newChef.getNazionalita());
			this.chefService.save(chef);
			return "redirect:/admin/chefs";
		}
		return "chefs/editChefForm";
	}
	
	@GetMapping("/admin/delete/chef/{id}")
	public String getDeleteChefConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefs/deleteChefConfirm";
	}
	
	@PostMapping("/admin/delete/chef/{id}")
	public String deleteChef(@PathVariable Long id) {
		this.chefService.deleteById(id);
		return "redirect:/admin/chefs";
	}
	
}