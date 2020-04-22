package com.mail.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.demo.model.User;

/**
 * 
 * @author Milanpreet Kaur
 *
 */
@Service
public class EmailService {

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, springboot also provide auto-configured functionality.
	 */
	private JavaMailSender mailSender;

	/**
	 * 
	 *  mailSender
	 */
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(User user) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send simpleMailMessage in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(user.getEmailAddress());
		simpleMailMessage.setSubject("Testing Email sending api application");
		simpleMailMessage.setText("Congratulations ! You have done it mate..");

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		mailSender.send(simpleMailMessage);
	}

	/**
	 * This function is used to send mail that contains a attachment any pdf or image.
	 */
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(user.getEmailAddress());
		helper.setSubject("Testing Email sending api application");
		helper.setText("Congratulations ! You have done it mate..");
		helper.setText("Please find the attached document below.");

		
		ClassPathResource classPathResource = new ClassPathResource("image.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		mailSender.send(message);
	}

}
