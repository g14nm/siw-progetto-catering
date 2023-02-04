package it.uniroma3.siw.siwprogettocatering.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Controller
public class AdminPiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@GetMapping
	public String getPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.findAll());
		return "piatti/piatti";
	}
	
}