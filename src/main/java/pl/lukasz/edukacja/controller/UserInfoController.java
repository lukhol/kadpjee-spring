package pl.lukasz.edukacja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.service.UserService;

@Controller
public class UserInfoController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/userDetails/{username}")
	public String userDetails2(@PathVariable("username") String username, Model model){
		model.addAttribute("nameofuser", username);
		User user = userService.findByLogin(username);
		model.addAttribute("user", user);
		return "userDetails";
	}
	
	@RequestMapping(value="/userDetails")
	public String userDetails(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	   	Object obj = auth.getCredentials();
	   	
		return "redirect:/userDetails/" + name;
	}
}
