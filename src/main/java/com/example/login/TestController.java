package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class TestController {
	
	private final UserService userService;
	
	@Autowired
	public TestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String showLogin(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@GetMapping("/landingPage")
	public String showLandingPage(Model model,@RequestParam("username") String username) {
		
		User user = userService.getUserByUsername(username);
		
;		if(user != null) {
			model.addAttribute("user", user);
		}else {
			
			model.addAttribute("errorUserNotFound", "Der Benutzer konnte nicht gefunden werden!");
		}
		return "landingPage";
	}
	
	@PostMapping("/")
	public String processLogin(Model model, User user) {
		User storedUser = userService.getUserByUsername(user.getUsername());
		if( storedUser != null && storedUser.getPassword().equals(user.getPassword())) {
			return "redirect:landingPage?username=" + user.getUsername();
		}else {
			model.addAttribute("error", "Falscher Benutzername oder Passwort");
			return "index";
		}
	}
	
	@PostMapping("/register")
	public String processRegister(User user, Model model) {
		

		if(userService.getUserByUsername(user.getUsername()) != null) {
			model.addAttribute("error", "Benutzername bereits vergeben!");
			return "register";
		}
		
		if(user.getUsername().length() == 0 || user.getPassword().length() == 0 || user.getEmail().length() == 0) {
			model.addAttribute("errorEmptyField", "Das Feld darf nicht leer sein!");
			return "register";
		}
		
		if(user.getPassword().equals(user.getRepeatPassword()) == false) {
			model.addAttribute("errorRepeatPassword", "Die Passwörter stimmen nicht überein!");
			return "register";
		}
		
		userService.registerUser(user);
		
		
		
		return "index";
	}
	
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	

}
