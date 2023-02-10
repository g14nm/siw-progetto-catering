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
import it.uniroma3.siw.siwprogettocatering.controller.validator.BuffetValidator;
import it.uniroma3.siw.siwprogettocatering.model.Buffet;
import it.uniroma3.siw.siwprogettocatering.service.BuffetService;
import it.uniroma3.siw.siwprogettocatering.service.ChefService;
import it.uniroma3.siw.siwprogettocatering.service.PiattoService;

@Controller
public class AdminBuffetController {
	
	final String UPLOAD_DIRECTORY = "foto/buffet/";
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private PiattoService piattoService;
	
	@GetMapping("/admin/buffets")
	public String getBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets/adminBuffets";
	}
	
	@GetMapping("/admin/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "buffets/buffetForm";
	}
	
	@PostMapping("/admin/buffet")
	public String saveBuffet(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model)
			throws IOException {
		this.buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			Buffet savedBuffet = this.buffetService.save(buffet);
			
			FileUploadUtil.saveFile(UPLOAD_DIRECTORY, savedBuffet.getId().toString(), multipartFile);
			return "redirect:/admin/buffets";
		}
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "buffets/buffetForm";
	}
	
	@GetMapping("/admin/edit/buffet/{id}")
	public String getEditBuffetForm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "buffets/editBuffetForm";
	}
	
	@PostMapping("/admin/edit/buffet/{id}")
	public String editBuffet(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet newBuffet, BindingResult bindingResult, Model model)
			throws IOException {
		Buffet buffet = this.buffetService.findById(id);
		
		if(!buffet.equals(newBuffet))
			this.buffetValidator.validate(newBuffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			buffet.setNome(newBuffet.getNome());
			buffet.setDescrizione(newBuffet.getDescrizione());
			buffet.setChef(newBuffet.getChef());
			buffet.setPiatti(newBuffet.getPiatti());
			this.buffetService.save(buffet);
			if(!multipartFile.isEmpty()) FileUploadUtil.saveFile(UPLOAD_DIRECTORY, buffet.getId().toString(), multipartFile);
			return "redirect:/admin/buffets";
		}
		model.addAttribute("chefs", this.chefService.findAll());
		model.addAttribute("piatti", this.piattoService.findAll());
		return "buffets/editBuffetForm";
	}
	
	@GetMapping("/admin/delete/buffet/{id}")
	public String getDeleteBuffetConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "buffets/deleteBuffetConfirm";
	}
	
	@PostMapping("/admin/delete/buffet/{id}")
	public String deleteBuffet(@PathVariable Long id, Model model) throws IOException {
		this.buffetService.deleteById(id);
		FileUploadUtil.deleteFile(UPLOAD_DIRECTORY, id.toString());
		return "redirect:/admin/buffets";
	}
	
}