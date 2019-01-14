package pl.lukasz.edukacja.service;

public interface EmailService {
	public boolean sendEmail(String recipientEmail, String subject, String content);
}
