package com.mail.demo.controller;

import javax.mail.MessagingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.demo.model.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import com.mail.demo.service.EmailService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This class contains a Email API developed using Spring Boot
 * 
 * @author Milanpreet Kaur
 *
 */
@RestController
public class UserRegisterController {

	/*
	 * @Autowired enables you to inject the object dependency implicitly.
	 */
	@Autowired
	private EmailService notifyService;

	@Autowired
	private EmailVo emailVo;

	/*
	 * Creating privates variable for name and email to reterive the value stored in application.proterties.
	 */

	@Value("${Name}")
	private String name;

	@Value("${emailAddress}")
	private String email;


	/**
	 * Using @GetMapping annotation to GET request from the postman.
	 */

	@GetMapping("/send-mail")
	public String send() {

		/*
		 * Creating a EmailVo with the help of EmailVo class that we have declared. Setting
		 * the name and Email address of the sender and retrieving the values
		 * stored in application.properties file.
		 */

		emailVo.setName(name);
		emailVo.setEmail(email); //emailVo.setEmail(email); Receiver's email address from application.properties

		/*
		 * Calling sendEmail() for Sending mail to the sender by using GET request.
		 */
		try {
			notifyService.sendEmail(emailVo);
		} catch (MailException exception) {
			System.out.println(exception);;  // If any exception comes then it will print the stacktrace of exception occured.
		}

		return "Congratulations! Your GET request is working fine.";
	}

	/**
	 * Using @RequestMapping annotation to POST value from the postman without attachment.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/send-mail-without-attachment")
	public String send(@RequestBody EmailVo emailVo){

		/*
		 * Setting the name,Email,header and emailBody through postman
		 */
		emailVo.setName(emailVo.getName());
		emailVo.setEmail(emailVo.getEmail());
		emailVo.setEmailHeader(emailVo.getEmailHeader());
		emailVo.setEmailBody(emailVo.getEmailBody());
		/*
		 * Calling sendEmailWithoutAttachment for Sending mail to the sender.
		 */
		try {
			notifyService.sendEmailWithoutAttachment(emailVo);
		} catch (MailException exception) {
			System.out.println(exception);
		}

		return "Congratulations! Your mail has been send to the user successfully.";
	}

	/**
	 * Using @RequestMapping annotation to POST value from the postman with attachment in the form of any file and json format.
	 */
	@PostMapping(value = "/sendmailwithattachment", consumes = {"multipart/mixed", "multipart/form-data"})
	public String file(@RequestPart(value = "files", required = true) MultipartFile files, String jsonFileVo) throws URISyntaxException {

		EmailVo emailVo = null;
		try{
			emailVo = new ObjectMapper().readValue(jsonFileVo, EmailVo.class ); // Converting the json format to java object.
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Rest API" + files);

		try {
			notifyService.sendEmailWithAttachment(emailVo, files);
		} catch (MailException | IOException | MessagingException exception) {
			System.out.println(exception);
		}

		/**
		 * emailVo prints the toString method present in EmailVo.
		 */
		return emailVo +
				"Congratulations! Your mail successfully sent to the user with the given attachment.";


	}


}
