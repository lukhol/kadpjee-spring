package pl.lukasz.edukacja.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukasz.edukacja.service.EmailService;

@Controller
public class EmailController {

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/sendEmailTest")
	public String sendEmailTest(){
		// takes input from e-mail form
        String recipientAddress = "email.do.springa@gmail.com";
        String subject = "Potwierdzenie rejestracji";
        String message = "Treść wiadomości tak dla testu.";
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        // sends the e-mail
        mailSender.send(email);
        
        return "redirect:/";
	}
	
	@RequestMapping(value="/sendEmailTestTwo")
	public String sendEmailTestTwo(){
		mailSender.send(new MimeMessagePreparator() {
			  public void prepare(MimeMessage mimeMessage) throws MessagingException {
			    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			    message.setFrom("email.do.springa@gmail.com");
			    message.setTo("email.do.springa@gmail.com");
			    message.setSubject("A file for you");
			    message.setText("<b>See the attached</b>", true);
			    //message.addAttachment("CoolStuff.doc", new File("CoolStuff.doc"));
			  }
			});
		return "redirect:/";
	}
	
	@RequestMapping(value="/sendEmailWithServiceClass")
	public String sendEmailTestThree(){
		emailService.sendEmail("lukholldz@wp.pl", "Tutuł", "Treść");
		return "redirect:/";
	}
	
}
