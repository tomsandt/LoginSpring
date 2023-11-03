package com.example.login.controller;

import com.example.login.Object.User;
import com.example.login.Object.UserTO;
import com.example.login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showLogin(Model model) {
		model.addAttribute("userTO", new UserTO());
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("userTO", new UserTO());
		return "register";
	}
	
	@GetMapping("/landingPage")
	public String showLandingPage(Model model,@RequestParam("username") String username) {
		
		User user = userService.findUserByUsername(username);
		
		if (user != null) {
			model.addAttribute("user", user);
		} else {
			
			model.addAttribute("errorUserNotFound", "The User could not be found.");
		}
		return "landingPage";
	}
	
	@PostMapping("/")
	public String processLogin(Model model, UserTO user) {
		User storedUser = userService.findUserByUsername(user.getUsername());
		if(storedUser == null) {
			model.addAttribute("errorUserDoesNotExist", "Unknown Username.");
			return "index";
		}

		final String hashedInputPwd = UserService.hashPassword(user.getPassword());
		final String hashedPwd = storedUser.getPassword();
        assert hashedInputPwd != null;
        if(!hashedInputPwd.equals(hashedPwd)) {
			model.addAttribute("errorFalsePassword", "Password is not correct.");
			return "index";
		}
		return "redirect:landingPage?username=" + user.getUsername();
	}
	
	@PostMapping("/register")
	public String processRegister(UserTO user, Model model) {
		

		if (userService.findUserByUsername(user.getUsername()) != null) {
			model.addAttribute("error", "Username already in use.");
			return "register";
		}
		
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
			model.addAttribute("errorEmptyField", "The Field can not be empty.");
			return "register";
		}
		
		if (!user.getPassword().equals(user.getRepeatPassword())) {
			model.addAttribute("errorRepeatPassword", "Password und Password repeat have to be equal.");
			return "register";
		}

		if (user.getBirthDate() == null) {
			model.addAttribute("errorBirthDateNull", "The Birth Date is not in the correct Format.");
			return "index";
		}

		final String hashPassword = UserService.hashPassword(user.getPassword());

		user.setPassword(hashPassword);
		
		userService.createUser(user);
		
		
		
		return "index";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	

}
