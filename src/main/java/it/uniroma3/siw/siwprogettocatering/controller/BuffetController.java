package it.uniroma3.siw.siwprogettocatering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.siwprogettocatering.service.BuffetService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@GetMapping("/buffets")
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets/buffets";
	}
	
	@GetMapping("/buffets/{id}")
	public String getBuffets(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "buffets/buffet";
	}
	
}