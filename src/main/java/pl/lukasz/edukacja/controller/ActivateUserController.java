package pl.lukasz.edukacja.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukasz.edukacja.domain.Activator;
import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.service.ActivatorService;
import pl.lukasz.edukacja.service.UserService;

@Controller
public class ActivateUserController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	ActivatorService activatorService;

	/*
	//localhost:8080/edukacja/activateUser?userId=5
	@RequestMapping(value="/activateUser")
	public String activateUser(Model model, HttpServletRequest request){
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		if(userId>0 && userService.getUser(userId) != null){
			User user = userService.getUser(userId);
			user.setEnabled(true);
			userService.editUserWithoutHashPassword(user);
		}
		return "activateUserGoesOk";
	}
	*/
	@RequestMapping(value="/activateUser/{code}")
	public String activateUserWithHashcode(@PathVariable("code") Integer code, Model model, HttpServletRequest request){
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		if(userId>0 && userService.getUser(userId) != null){
			User user = userService.getUser(userId);
			Activator activator = activatorService.getActivator(user.getId());
			if(code == activator.getCode()){
				user.setEnabled(true);
				userService.editUserWithoutHashPassword(user);
				activatorService.deleteActivator(userId);
				System.out.println("TEST");
				return "activateUserGoesOk";
			}
			if(code != activator.getCode())
				System.out.println("Problem z kodem rejestracji");
		}
		return "activateUserGoesWrong";
	}
}
