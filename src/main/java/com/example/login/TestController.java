package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class TestController {  // rename TestController -> Controller using Idea renaming feature
	
	private final UserRepository userService;
	
	@Autowired
	public TestController(UserRepository userService) {
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
		
		User user = userService.findUserByUsername(username);
		
		if (user != null) {
			model.addAttribute("user", user);
		} else {
			model.addAttribute("errorUserNotFound", "Der Benutzer konnte nicht gefunden werden!");
		}
		return "landingPage";
	}
	
	@PostMapping("/")
	public String processLogin(Model model, User user) {
		User storedUser = userService.findUserByUsername(user.getUsername());
		/*
		if ( storedUser != null && storedUser.getPassword().equals(UserService.hashPassword(user.getPassword()))) {
			return "redirect:landingPage?username=" + user.getUsername();
		} else {
			model.addAttribute("error", "Falscher Benutzername oder Passwort");
			return "index";
		}
		*/
		// Tom, your code above is good :)
		// Just as a general rule of thumb on how to check error cases:
		// - first check all error cases - case by case - and abort if there is an error
		// - then process the success case
		// This makes the code clearer and easier to understand.
		// Actually in processRegister() you already did it this way :)
		// Example for the code above:
		
		if ( storedUser == null ) {
			model.addAttribute("error", "Unbekannter Benutzername");
			return "index";
		}
		
		final String hashedPwd = storedUser.getPassword();
		assert hashedPwd != null : "hashedPwd is null: processRegister() should have prevented this!";
		if ( !hashedPwd.equals(UserService.hashPassword(user.getPassword())) ) {
			model.addAttribute("error", "Falsches Passwort");
			return "index";
		}

		return "redirect:landingPage?username=" +user.getUsername();
	}
	
	@PostMapping("/register")
	public String processRegister(User user, Model model) {

		if (userService.findUserByUsername(user.getUsername()) != null) {
			// Sorry for my doubts regarding this on Telegram :(
			model.addAttribute("error", "Benutzername bereits vergeben!");
			return "register";
		}
		
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
			model.addAttribute("errorEmptyField", "Das Feld darf nicht leer sein!");
			return "register";
		}
		
		if(user.getPassword().equals(user.getRepeatPassword()) == false) {
			model.addAttribute("errorRepeatPassword", "Die Passwörter stimmen nicht überein!");
			return "register";
		}

		final String hashPassword = UserService.hashPassword(user.getPassword());
		user.setPassword(hashPassword);
		userService.saveUser(user.getUsername(), hashPassword, user.getBenutzeralter(), user.getEmail());
		
		return "index";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}
