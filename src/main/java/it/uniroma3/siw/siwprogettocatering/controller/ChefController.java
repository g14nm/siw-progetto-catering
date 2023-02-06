package it.uniroma3.siw.siwprogettocatering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocatering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/chefs")
	public String getChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefs/chefs";
	}
	
}