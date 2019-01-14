package pl.lukasz.edukcja.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.lukasz.edukacja.domain.User;
import pl.lukasz.edukacja.service.*;


import org.apache.commons.validator.routines.EmailValidator;

public class UserValidator implements Validator{

	private UserService userService;
	
	public UserValidator(UserService userService){
			this.userService = userService;
	}
	
	private int minPasswordLength;
	private int maxPasswordLength;
	private static String loginRegex = "[a-zA-Z]*";
	private static String digitsRegex = ".*\\p{Digit}.*";
	private static String specialCharactersRegex = ".*[!£$#%^&*@?<>+_].*";

	EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
	}

	public void validate(User user, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "login", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "firstname", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "lastname", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "pesel", "error.field.reqired");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "error.field.reqired");
		
		if (errors.getErrorCount() == 0) {
			if (StringUtils.hasText(user.getEmail()) && emailValidator.isValid(user.getEmail()) == false) {
				errors.rejectValue("email", "error.email.invalid");
			}
			
			if(StringUtils.hasText(user.getLogin()) && user.getLogin().length() < 5){
				errors.rejectValue("login", "error.login.toshort");
			}
			
			if(userService.findByLogin(user.getLogin()) != null){
				errors.rejectValue("login", "error.login.invalid");
			}
			
		}
	}
	
	public void setMinPasswordLength(int minPasswordLength) {
		this.minPasswordLength = minPasswordLength;
	}

	public void setMaxPasswordLength(int maxPasswordLength) {
		this.maxPasswordLength = maxPasswordLength;
	}

}
