package it.uniroma3.siw.siwprogettocatering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@GetMapping("/piatti")
	public String getPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.findAll());
		return "piatti/piatti";
	}
	
	@GetMapping("/piatti/{id}")
	public String getBuffets(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatti/piatto";
	}
	
}