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

import it.uniroma3.siw.siwprogettocatering.controller.validator.PiattoValidator;
import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.service.IngredienteService;
import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Controller
public class AdminPiattoController {
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/admin/piatti")
	public String getPiattos(Model model) {
		model.addAttribute("piatti", this.piattoService.findAll());
		return "piatti/adminPiatti";
	}
	
	@GetMapping("/admin/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "piatti/piattoForm";
	}
	
	@PostMapping("/admin/piatto")
	public String savePiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.piattoService.save(piatto);
			return "redirect:/admin/piatti";
		}
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "piatti/piattoForm";
	}
	
	@GetMapping("/admin/edit/piatto/{id}")
	public String getEditPiattoForm(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "piatti/editPiattoForm";
	}
	
	@PostMapping("/admin/edit/piatto/{id}")
	public String editPiatto(@PathVariable Long id,@Valid @ModelAttribute("piatto") Piatto newPiatto, BindingResult bindingResult, Model model) {
		Piatto piatto = this.piattoService.findById(id);
		if(!piatto.equals(newPiatto))
			this.piattoValidator.validate(newPiatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			piatto.setNome(newPiatto.getNome());
			piatto.setDescrizione(newPiatto.getDescrizione());
			piatto.setIngredienti(newPiatto.getIngredienti());
			this.piattoService.save(piatto);
			return "redirect:/admin/piatti";
		}
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "piatti/editPiattoForm";
	}
	
	@GetMapping("/admin/delete/piatto/{id}")
	public String getDeletePiattoConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.findById(id));
		return "piatti/deletePiattoConfirm";
	}
	
	@PostMapping("/admin/delete/piatto/{id}")
	public String deletePiatto(@PathVariable Long id) {
		this.piattoService.deleteById(id);
		return "redirect:/admin/piatti";
	}
	
}