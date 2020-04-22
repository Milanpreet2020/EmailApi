package com.mail.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.demo.model.User;
import com.mail.demo.service.EmailService;

/**
 * This class contains a Email API developed using Spring Boot
 * 
 * @author Milanpreet Kaur
 *
 */
@RestController
public class UserRegisterController {

	@Autowired
	private EmailService notifyService;

	@Autowired
	private User user;

	/*
	 * Creating privates variable for name and email to reterive the value stored in application.proterties.
	 */
	@Value("${firstName}")
	private String fName;

	@Value("${lastName}")
	private String lName;

	@Value("${emailAddress}")
	private String email;


	/**
	 * 
	 * Using @RequestMapping annotation to GET the value.
	 */
	@RequestMapping("send-mail")

	public String send() {

		/*
		 * Creating a User with the help of User class that we have declared. Setting
		 * the First,Last and Email address of the sender and retrieving the values
		 * stored in application.properties file.
		 */

		user.setFirstName(fName);
		user.setLastName(lName);
		user.setEmailAddress(email); //Receiver's email address from application.properties

		/*
		 * Calling sendEmail() for Sending mail to the sender.
		 */
		try {
			notifyService.sendEmail(user);
		} catch (MailException exception) {
			System.out.println(exception);
		}
		return "Congratulations! Your mail has been send to the user successfully.";
	}

	/**
	 * 
	 * @return
	 * @throws MessagingException in any case if attachment isnt present
	 */
	@RequestMapping("send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {

		/*
		 * Creating a User with the help of User class that we have declared.
		 */

		user.setFirstName(fName);
		user.setLastName(lName);
		user.setEmailAddress(email);
		//user email address

		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {
			notifyService.sendEmailWithAttachment(user);
		} catch (MailException exception) {
			System.out.println(exception);
		}
		return "Congratulations! Your mail successfully sent to the user with the given attachment.";
	}
}
