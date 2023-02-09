package it.uniroma3.siw.siwprogettocatering.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwprogettocatering.authentication.controller.validator.CredentialsValidator;
import it.uniroma3.siw.siwprogettocatering.authentication.controller.validator.UserValidator;
import it.uniroma3.siw.siwprogettocatering.authentication.model.Credentials;
import it.uniroma3.siw.siwprogettocatering.authentication.model.User;
import it.uniroma3.siw.siwprogettocatering.authentication.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@GetMapping("/register")
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "login/registerUser";
	}
	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "login/loginForm";
	}
	
	@GetMapping("/login/error")
	public String showLoginErrorForm (Model model) {
		return "login/loginErrorForm";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "redirect:/";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "redirect:/admin/home";
        }
        return "redirect:/";
    }
	
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "login/registrationSuccessful";
        }
        return "login/registerUser";
    }
    
}