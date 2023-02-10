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
import it.uniroma3.siw.siwprogettocatering.controller.validator.ChefValidator;
import it.uniroma3.siw.siwprogettocatering.model.Chef;
import it.uniroma3.siw.siwprogettocatering.service.ChefService;

@Controller
public class AdminChefController {
	
	final String UPLOAD_DIRECTORY = "foto/chef/";
	
	@Autowired
	private ChefValidator chefValidator;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/admin/chefs")
	public String getChefs(Model model) {
		model.addAttribute("chefs", this.chefService.findAll());
		return "chefs/adminChefs";
	}
	
	@GetMapping("/admin/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefs/chefForm";
	}
	
	@PostMapping("/admin/chef")
	public String saveChef(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model)
		throws IOException {
		this.chefValidator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			Chef savedChef = this.chefService.save(chef);
			
			FileUploadUtil.saveFile(UPLOAD_DIRECTORY, savedChef.getId().toString(), multipartFile);
			return "redirect:/admin/chefs";
		}
		return "chefs/chefForm";
	}
	
	@GetMapping("/admin/edit/chef/{id}")
	public String getEditChefForm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefs/editChefForm";
	}
	
	@PostMapping("/admin/edit/chef/{id}")
	public String editChef(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id, @Valid @ModelAttribute("chef") Chef newChef, BindingResult bindingResult, Model model)
			throws IOException {
		Chef chef = this.chefService.findById(id);
		if(!chef.equals(newChef))
			this.chefValidator.validate(newChef, bindingResult);
		if(!bindingResult.hasErrors()) {
			chef.setNome(newChef.getNome());
			chef.setCognome(newChef.getCognome());
			chef.setNazionalita(newChef.getNazionalita());
			this.chefService.save(chef);
			if(!multipartFile.isEmpty()) FileUploadUtil.saveFile(UPLOAD_DIRECTORY, chef.getId().toString(), multipartFile);
			return "redirect:/admin/chefs";
		}
		return "chefs/editChefForm";
	}
	
	@GetMapping("/admin/delete/chef/{id}")
	public String getDeleteChefConfirm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		return "chefs/deleteChefConfirm";
	}
	
	@PostMapping("/admin/delete/chef/{id}")
	public String deleteChef(@PathVariable Long id, Model model) throws IOException {
		this.chefService.deleteById(id);
		FileUploadUtil.deleteFile(UPLOAD_DIRECTORY, id.toString());
		return "redirect:/admin/chefs";
	}
	
}