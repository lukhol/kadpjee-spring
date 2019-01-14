package pl.lukasz.edukacja.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import pl.lukasz.edukacja.domain.Activator;
import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.service.ActivatorService;
import pl.lukasz.edukacja.service.UserService;
import pl.lukasz.edukcja.validator.UserValidator;

@Controller
@SessionAttributes("userWithRole")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	ActivatorService activatorService;
	
	@RequestMapping(value="/listOfUsers")
	public String listUser(Model model){
		List<User> listOfUsers = userService.listUser();
		model.addAttribute("listOfUsers", listOfUsers);
		return "listOfUsers";
	}
	
	@RequestMapping(value="/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId, Model model){
		userService.removeUser(userId);
		return "redirect:/listOfUsers.html";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUserGet(Model model, HttpServletRequest request){
		
		int userId = ServletRequestUtils.getIntParameter(request, "userId", -1);
		System.out.println(userId);
		
		if(userId > 0){
			User user = userService.getUser(userId);
			model.addAttribute("userToAdd",  user);
			User userWithRole = new User();
			userWithRole.setUserRole(user.getUserRole());
			model.addAttribute("userWithRole", userWithRole);
			//Test:
			System.out.println(user.getPassword());
		}
		else{
			model.addAttribute("userToAdd", new User());
			model.addAttribute("userWithRole", new User());
		}
		return "addUser";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserPost(@ModelAttribute("userWithRole") User userWithRole,
			@ModelAttribute("userToAdd") User addedUser, 
			HttpServletRequest request, BindingResult result, @RequestParam("picture") MultipartFile file) throws Exception{
		
		if(addedUser.getId() == 0){
			UserValidator userValidator = new UserValidator(userService);
			userValidator.validate(addedUser, result);
			if(result.getErrorCount() == 0){
				if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
				addedUser.setEnabled(false);
				userService.addUser(addedUser);
				//sendActivationEmail(addedUser.getId(), addedUser.getEmail());
				activatorEmailSend(addedUser);
			}
			else{
				return "addUser";
			}
		}else{
			if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
			addedUser.setUserRole(userWithRole.getUserRole());
			userService.editUserWithoutHashPassword(addedUser);
			//Test:
			System.out.println(addedUser.getPassword());
		}
		return "redirect:/listOfUsers.html";
	}
	
	private void sendActivationEmail(int userId, String to){
		String recipientAddress = to;
        String subject = "Potwierdzenie rejestracji";
        String message = "W celu potwierdzenia rejestracji prosimy klinać w link: ";
        message += "http://localhost:8080/edukacja/activateUser?userId=" + userId;
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        mailSender.send(email);
	}
	
	private void activatorEmailSend(User user){
		Activator activator = new Activator();
		activator.setUser(user);
		int activatorCode = user.hashCode();
		activator.setCode(activatorCode);
		activatorService.addActivator(activator);
		
		String activateLink = "http://localhost:8080/edukacja/activateUser/" + activatorCode + "?userId=" + user.getId();
		String message = "W celu potwierdzenie rejestracji proszę kliknąć w link: ";
		message += activateLink;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Potwierdzenie rejestracji");
		email.setText(message);
		
		mailSender.send(email);
	}
}

/*
@RequestMapping(value = "/addUser", method = RequestMethod.GET)
public String addUser(Model model, HttpServletRequest request) {
	if(request.getAttribute("userToAdd") == null){
		User userToAdd = new User();
		model.addAttribute("userToAdd", userToAdd);
	}
	else{ 
		model.addAttribute("userToAdd", request.getAttribute("userToAdd"));
	}
	return "addUser";
}

@RequestMapping(value = "/addUser", method = RequestMethod.POST)
public String processAddUser(@ModelAttribute("userToAdd") User addedUser, HttpServletRequest request, BindingResult result,
		@RequestParam("picture") MultipartFile file) throws Exception{
	UserValidator userValidator = new UserValidator(userService);
	userValidator.validate(addedUser, result);
	if(result.getErrorCount() == 0){
		if(addedUser.getId() == 0) {
			if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
			userService.addUser(addedUser);
		}
		else {
			if(request.getAttribute("userToAdd") != null){
				//Prowizoryczne nie stracenie ról dla edytowanego usera
				//User user = (User)request.getAttribute("userToAdd");
				//addedUser.setUserRole(user.getUserRole());
				//addedUser.setId(user.getId());
				//if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
				//userService.editUser(addedUser);
				//System.out.println("test");
			}
		}
		return "redirect:/listOfUsers.html";
	}
	return "addUser";
}

@RequestMapping(value="/editUser/{userId}")
public String editUser(@PathVariable("userId") Integer userId, Model model, HttpServletRequest request){
	User editingUser = userService.getUser(userId);
	editingUser.setPassword("");
	request.setAttribute("userToAdd", editingUser);
	request.setAttribute("userId", userId);
	return "forward:/addUser";
}
*/

/*
UserValidator userValidator = new UserValidator(userService);
userValidator.validate(addedUser, result);
if(result.getErrorCount() == 0){
	if(addedUser.getId() == 0) {
		if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
		userService.addUser(addedUser);
	}
	else {
		if(file.getBytes() != null) addedUser.setPicture(file.getBytes());
		userService.editUser(addedUser);
	}
	return "redirect:/listOfUsers.html";
}
return "addUser";
*/


