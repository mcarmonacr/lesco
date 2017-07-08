package com.lesco.diccionario.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.helper.RandomGeneratorHelper;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.ContactForm;
import com.lesco.diccionario.pojo.RegisterForm;
import com.lesco.diccionario.utils.LescoConstants;
import com.lesco.diccionario.utils.SHAEncryption;
import com.lesco.diccionario.utils.SendMailTLS;

/**
 * Handles all the registry related operations
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/registro")
public class RegisterController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SHAEncryption shaEncryption;
	
	@Autowired
	private RandomGeneratorHelper randomGenerator;
	
	@Autowired
	private SendMailTLS sendMailTLS;
	
	/**
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/nuevoUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody nuevoUsuario(@RequestBody RegisterForm registerForm){
		
		logger.debug("RegisterController - nuevoUsuario() - Start");
		
		AjaxResponseBody response = new AjaxResponseBody();
		
		try{
			//Saves the user to the database
			String resultadoSalvar= salvarUsuario(registerForm);
			
			//Response toggle based on the save return
			if("Success".equals(resultadoSalvar)){
				response.setCode("000");
				response.setMessage("Success");
			}else{
				response.setCode("999");
				response.setMessage("Failure");
			}
		}catch(Exception e){
			logger.debug("RegisterController - nuevoUsuario() - Error", e);
		}

		logger.debug("RegisterController - nuevoUsuario() - End");
		
		return response;
	}
	
	/**
	 * Verifies is the userName entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarUsuario() - Start");
		
		try{
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
		}catch(Exception e){
			logger.debug("RegisterController - verificarUsuario() - Error", e);
		}

		logger.debug("RegisterController - verificarUsuario() - End");
		
		return result;
	}
	
	/**
	 * 
	 * Verifies is the emailAddress entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/verificarCorreo", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarCorreo(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarCorreo() - Start");
		
		try{
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
		}catch(Exception e){
			logger.debug("RegisterController - verificarCorreo() - Error", e);
		}
				
		logger.debug("RegisterController - verificarCorreo() - End");
		
		return result;
	}
	
	/**
	 * Creates a new password and then send an email to the user with that new information
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: emailAddress
	 */
	@RequestMapping(value= "/recuperarPassword", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody recuperarPassword(@RequestBody Map<String, String> json){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		logger.debug("RegisterController - recuperarPassword() - Start");
		
		try{
			if (json.get("emailAddress") != null){	
				
				String emailAddress= json.get("emailAddress");

				//Generate a random string of the given length
				String newPassword= randomGenerator.randomString(LescoConstants.RANDOM_PASSWORD_LENGHT);
				
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
						contactForm.setContactSubject("Diccionario LESCO - Cambio de Contraseña");
						
						//Send the email with the given parameters
						String sendEmailResponse = sendMailTLS.sendPasswordRecoveryMail(contactForm, newPassword);
						
						//Response toggle based on the save return
						if(LescoConstants.SUCCESS_MESSAGE.equals(sendEmailResponse)){
							ajaxResponse.setCode(LescoConstants.SUCCESS_CODE);
							ajaxResponse.setMessage(LescoConstants.SUCCESS_MESSAGE);
						}else{
							ajaxResponse.setCode(LescoConstants.FAILURE_CODE);
							ajaxResponse.setMessage(LescoConstants.FAILURE_MESSAGE);
						}
				 }
			}
		}catch(Exception e){
			logger.debug("RegisterController - recuperarPassword() - Error", e);
		}
		
		logger.debug("RegisterController - recuperarPassword() - End");
		
		return ajaxResponse;
	} 
	
	/**** Private Methods ****/
	
	/**
	 * Stores the new user into the database
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	private String salvarUsuario(RegisterForm registerForm){
		
		logger.debug("RegisterController - salvarUsuario() - Start");
		
		//String to the return, with the operation result
		String isUserSaved = LescoConstants.FAILURE_MESSAGE;
		
		try{
			//Validates that all values that come from the form
			if(registerForm.getUserName() != null && registerForm.getEmailAddress() != null && registerForm.getPassword() != null && 
					registerForm.getPasswordConfirmation() != null	&& registerForm.getBirthDate() != null && registerForm.getTermsAndConditions() != null){
						
				//Checks if the userName already exists
				//TODO Add the validation of the email
				if(userDAO.checkUserName(registerForm.getUserName()).equals(false)){
				
					//Get unique random salt which will be used to encryp the user password
					byte[] salt= SHAEncryption.getSalt();
					
					//New Profile Detail
					ProfileDetail profileDetail = new ProfileDetail();
					profileDetail.setBirthDate(registerForm.getBirthDate());
					profileDetail.setTermsAndConditions(registerForm.getTermsAndConditions());
					profileDetail.setEmail(registerForm.getEmailAddress());
					
					//New User Profile
					UserProfile userProfile = new UserProfile();
					userProfile.setSalt(salt);
					userProfile.setUserName(registerForm.getUserName());
					userProfile.setUserPassword(shaEncryption.getHashedPassword(registerForm.getPassword(), salt));
					
					//Checks if the user should be administrator
					if(registerForm.getUserName().equals("administrador")){
						userProfile.setUserRole("administrator");
					}
					
					//Because this two instances have a one-to-one relationship, this needs to be done
					userProfile.setProfileDetail(profileDetail);
					profileDetail.setUserProfile(userProfile);
					
					//This saves both, the User Profile and the Profile Detail instances into the DB
					userDAO.save(userProfile);
					
					//If I wanted to get the ID, I'd have to do something like:
					//category.getId();
				}
				isUserSaved= "Success";
			}else{
				isUserSaved= "Failure";
			}
		}catch(Exception e){
			logger.debug("RegisterController - salvarUsuario() - Error", e);
		}

		logger.debug("RegisterController - salvarUsuario() - Start");
		
		return isUserSaved;
	}
}