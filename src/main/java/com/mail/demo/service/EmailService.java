package com.mail.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.mail.demo.model.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 
 * @author Milanpreet Kaur
 *
 */
@Service        // It uses to mark the class as a service provider.
@RequestMapping
public class EmailService {

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, springboot also provide auto-configured functionality.
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 *  mailSender
	 */
	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * @param
	 * @throws MailException
	 */

	public void sendEmail(EmailVo emailVo) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send simpleMailMessage in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(emailVo.getEmail());
		simpleMailMessage.setSubject("Testing API");
		simpleMailMessage.setText("Congratulations! GET request works fine");

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		mailSender.send(simpleMailMessage);

	}
	public void sendEmailWithoutAttachment(EmailVo emailVo) throws MailException {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(emailVo.getEmail());
		simpleMailMessage.setSubject(emailVo.getEmailHeader());
		simpleMailMessage.setText(emailVo.getEmailBody());
		mailSender.send(simpleMailMessage);

	}

	/**
	 * This function is used to send mail that contains a attachment any pdf or image or text.
	 */
	public void sendEmailWithAttachment(EmailVo emailVo, MultipartFile files) throws MailException, MessagingException, IOException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(emailVo.getEmail());
		helper.setSubject(emailVo.getEmailHeader());
		helper.setText(emailVo.getEmailBody());

		MultipartFile a = files;
	    File convFile = new File(a.getOriginalFilename());
	    convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(a.getBytes());
		fos.close();
		String emailContent = convFile.toString();

		ClassPathResource classPathResource = new ClassPathResource(emailContent);
		helper.addAttachment(classPathResource.getPath().toString(), convFile);

		mailSender.send(message);
	}

}
