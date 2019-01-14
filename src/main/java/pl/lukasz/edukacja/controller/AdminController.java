package pl.lukasz.edukacja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin**")
public class AdminController {
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String adminPage(Model model){
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page!");
		return "adminPage";
	}
	
	@RequestMapping(value = "/adminPageTwo", method = RequestMethod.GET)
	public String adminPageTwo(Model model){
		model.addAttribute("title", "drugi komunikat");
		model.addAttribute("message", "test blebleble!");
		return "adminPageTwo";
	}
	
}

