package it.uniroma3.siw.siwprogettocatering.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.siwprogettocatering.FileUploadUtil;
import it.uniroma3.siw.siwprogettocatering.controller.validator.PiattoValidator;
import it.uniroma3.siw.siwprogettocatering.model.Piatto;
import it.uniroma3.siw.siwprogettocatering.service.IngredienteService;
import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Controller
public class AdminPiattoController {
	
	final String UPLOAD_DIRECTORY = "foto/piatti/";
	
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
	public String savePiatto(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model)
			throws IOException {
		this.piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			Piatto savedPiatto = this.piattoService.save(piatto);
			
			FileUploadUtil.saveFile(UPLOAD_DIRECTORY, savedPiatto.getId().toString(), multipartFile);
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
	public String editPiatto(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id, @Valid @ModelAttribute("piatto") Piatto newPiatto, BindingResult bindingResult, Model model)
			throws IOException {
		Piatto piatto = this.piattoService.findById(id);
		if(!piatto.equals(newPiatto))
			this.piattoValidator.validate(newPiatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			piatto.setNome(newPiatto.getNome());
			piatto.setDescrizione(newPiatto.getDescrizione());
			piatto.removeFromIngredienti();
			piatto.setIngredienti(newPiatto.getIngredienti());
			this.piattoService.save(piatto);
			if(!multipartFile.isEmpty()) FileUploadUtil.saveFile(UPLOAD_DIRECTORY, piatto.getId().toString(), multipartFile);
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
	public String deletePiatto(@PathVariable Long id, Model model) throws IOException {
		this.piattoService.deleteById(id);
		FileUploadUtil.deleteFile(UPLOAD_DIRECTORY, id.toString());
		return "redirect:/admin/piatti";
	}
	
}