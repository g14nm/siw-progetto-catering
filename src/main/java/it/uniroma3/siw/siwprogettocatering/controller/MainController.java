package it.uniroma3.siw.siwprogettocatering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
			return "indexes/index";
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String adminIndex(Model model) {
		return "indexes/adminIndex";
	}
	
}