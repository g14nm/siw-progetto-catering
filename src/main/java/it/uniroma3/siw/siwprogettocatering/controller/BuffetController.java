package it.uniroma3.siw.siwprogettocatering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocatering.service.BuffetService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@GetMapping
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets/buffets";
	}
	
}