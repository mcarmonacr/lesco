package com.lesco.diccionario.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lesco.diccionario.dao.RequestDAO;
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.dao.WordDAO;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.Request;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RequestForm;

/**
 * Handles all the request related operations
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/solicitud")
public class RequestController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RequestController.class);

	@Autowired
	private UserDAO userDAO;
		
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private WordDAO wordDAO;
	
	/**
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/agregarSolicitud", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarSolicitud(@RequestBody RequestForm registerForm, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		
		logger.debug("RegisterController - agregarSolicitud() - Start");
		
		AjaxResponseBody response = new AjaxResponseBody();

		//Saves the user to the database
		String resultadoSalvar= salvarSolicitud(registerForm, httpRequest);
		
		//Response toggle based on the save return
		if("Success".equals(resultadoSalvar)){
			response.setCode("000");
			response.setMessage("Success");
		}else{
			response.setCode("999");
			response.setMessage("Failure");
		}
		
		logger.debug("RegisterController - agregarSolicitud() - End");
		
		return response;
	}
	
	/**
	 * Verifies is the userName entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 *//*
	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody RequestForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarUsuario() - Start");
		
		//Validate input
		if(registerForm.getUserName() != null && registerForm.getUserName().length() != 0){
			
			//Checks if the input user name already exists in the database
			if(userDAO.checkUserName(registerForm.getUserName().trim()) == false){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		}else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarUsuario() - End");
		
		return result;
	}*/
	
	/**
	 * 
	 * Verifies is the emailAddress entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 *//*
	@RequestMapping(value= "/verificarCorreo", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarCorreo(@RequestBody RequestForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarCorreo() - Start");
		
		//Validate input
		if(registerForm.getEmailAddress() != null && registerForm.getEmailAddress().length() != 0){
			
			//Checks if the input user name already exists in the database
			if(userDAO.checkEmailAddress(registerForm.getEmailAddress().trim()) == false){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		}else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarCorreo() - End");
		
		return result;
	}*/
	
	/**
	 * Creates a new password and then send an email to the user with that new information
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: emailAddress
	 *//*
	@RequestMapping(value= "/recuperarPassword", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody recuperarPassword(@RequestBody Map<String, String> json){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		logger.debug("RegisterController - recuperarPassword() - Start");
		
		if (json.get("emailAddress") != null){	
			
			String emailAddress= json.get("emailAddress");
		
			//TODO define a global constants class
			//Generate a random string of the given length
			String newPassword= randomGenerator.randomString(10);
			
			//Verifies that the email address exists in the database
			 if(userDAO.checkEmailAddress(emailAddress.trim()) == true){
				 
					//Obtain the User that belongs to the email
					ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(emailAddress);
					
					//Get the user's profile
					UserProfile userProfile = userDAO.findUserProfileById(profileDetailQuery.getProfileDetailId());
					
					//Set the new password
					userProfile.setUserPassword(shaEncryption.getHashedPassword(newPassword, userProfile.getSalt()));
					
					//Update the profile in the database
					userDAO.update(userProfile);
					
					//New contact form
					ContactForm contactForm = new ContactForm();
					contactForm.setContactEmail(emailAddress.trim());
					//contactForm.setContactMessage(contactMessage);
					contactForm.setContactName(userProfile.getUserName());
					contactForm.setContactSubject("Diccionario LESCO - Cambio de Contraseņa");
					
					//Send the email with the given parameters
					String sendEmailResponse = sendMailTLS.sendPasswordRecoveryMail(contactForm, newPassword);
					
					//Response toggle based on the save return
					if("success".equals(sendEmailResponse)){
						ajaxResponse.setCode("000");
						ajaxResponse.setMessage("Success");
					}else{
						ajaxResponse.setCode("999");
						ajaxResponse.setMessage("Error");
					}
			 }
		}		
		logger.debug("RegisterController - recuperarPassword() - End");
		
		return ajaxResponse;
	} */
	
	/**** Private Methods ****/
	
	/**
	 * Stores the new request into the database
	 * 
	 * @param requestForm. Contains fields: wordName, description
	 */
	private String salvarSolicitud(RequestForm requestForm, HttpServletRequest httpRequest){
		
		logger.debug("RegisterController - salvarSolicitud() - Start");
		
		//String to the return, with the operation result
		String isUserSaved;
		
		//Validates that all values that come from the form
		if(requestForm.getWordName() != null ){
					
			//Checks if the userName already exists in the word and request table
			//TODO Add the validation of the email
			if(requestDAO.checkWordName(requestForm.getWordName().trim()).equals(false) && wordDAO.checkWordName(requestForm.getWordName().trim()).equals(false)){

				//Get user session
				HttpSession session = httpRequest.getSession();
				
				//Get the current logged in user emailAddress
				String userEmail = session.getAttribute("userEmail").toString();
				
				//Obtain the User that belongs to the email
				ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
				ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
				UserProfile userProfile = ProfileDetailReference.getUserProfile();
				
				//New Request
				Request request = new Request();
				request.setDescription(requestForm.getDescription());
				request.setWordName(requestForm.getWordName());
				request.setUserProfile(userProfile);
				
				//This saves both, the User Profile and the Request instances into the DB
				requestDAO.save(request);
				
				//If I wanted to get the ID, I'd have to do something like:
				//request.getId();
			}
			isUserSaved= "Success";
		}else{
			isUserSaved= "Failure";
		}
		
		logger.debug("RegisterController - salvarSolicitud() - Start");
		
		return isUserSaved;
	}
}