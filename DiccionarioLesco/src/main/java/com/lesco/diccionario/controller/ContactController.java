package com.lesco.diccionario.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.ContactForm;
import com.lesco.diccionario.utils.LescoConstants;
import com.lesco.diccionario.utils.SendMailTLS;

/**
 * Handles all the contact related logic
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

@Controller
@RequestMapping("/contacto")
public class ContactController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(ContactController.class);
	
	@Autowired
	private SendMailTLS sendMailTLS;

	/**
	 * Service that sends a contact email to the site administrator.
	 * Type: Json POST method
	 * 
	 * @param contactForm. Contains fields: contactName, contactEmail, contactSubject, contactMessage.
	 */
	@RequestMapping(value= "/enviarFormularioContacto", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody enviarFormularioContacto(@RequestBody ContactForm contactForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("ContactController - enviarFormularioContacto() - Start");
		
		//Generic Ajax response body
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		//Send the email message
		String sendEmailResponse = sendMailTLS.sendMail(contactForm);
		
		//String sendEmailResponse = "success";
		
		//Response toggle based on the save return
		if(LescoConstants.SUCCESS_MESSAGE.equals(sendEmailResponse)){
			ajaxResponse.setCode(LescoConstants.SUCCESS_CODE);
			ajaxResponse.setMessage(LescoConstants.SUCCESS_MESSAGE);
		}else{
			ajaxResponse.setCode(LescoConstants.ERROR_CODE);
			ajaxResponse.setMessage(LescoConstants.ERROR_MESSAGE);
		}
		
		logger.debug("ContactController - enviarFormularioContacto() - End");
		
		return ajaxResponse;
	}
}
