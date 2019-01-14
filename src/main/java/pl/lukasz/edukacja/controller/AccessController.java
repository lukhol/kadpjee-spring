package pl.lukasz.edukacja.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessController {
	@RequestMapping(value="/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model, HttpServletRequest request){
		model.addAttribute("atrybut1", "Access denied. Come back to main site: <a href=\"/edukacja\">Home</a>");
		System.out.println("getPathInfo: " + request.getPathInfo());
		System.out.println("getContextPath: " + request.getContextPath());
		System.out.println("getPathTranlated: " + request.getPathTranslated());
		System.out.println("getRequestURI: " + request.getRequestURI());
		System.out.println("getRequestURL" + request.getRequestURL());
		return "accessDenied";
	}
	
	@RequestMapping(value="/whoIsLogIn")
	public String whoIsLogIn(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    System.out.println("name: " + name);
		return "home";
	}
}
