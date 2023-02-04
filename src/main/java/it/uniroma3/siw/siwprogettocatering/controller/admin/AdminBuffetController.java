package it.uniroma3.siw.siwprogettocatering.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocatering.service.BuffetService;

@Controller
public class AdminBuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@GetMapping
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets/buffets";
	}
	
}