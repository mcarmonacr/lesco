/**
 * 
 */
package com.lesco.diccionario.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.lesco.diccionario.controller.ContactController;
import com.lesco.diccionario.pojo.ContactForm;

/**
 * @author Mario Alonso Carmona Dinarte
 *
 */
public class SendMailTLS {

	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(SendMailTLS.class);
	
	public String sendMail(ContactForm contactForm) {

		final String username = "diccionariolesco@gmail.com";
		final String password = "diccionario";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("diccionariolesco@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("diccionariolesco@gmail.com"));
			message.setSubject(contactForm.getContactSubject());
			message.setText(" From: " + contactForm.getContactName()
				+ "\n\n Email: "+ contactForm.getContactEmail()
				+ "\n\n Message: " + contactForm.getContactMessage());

			Transport.send(message);

			//System.out.println("Done");

		} catch (MessagingException e) {
			logger.debug("SendMailTLS - admin() - End");
		}
		
		return "success";
	}
}
