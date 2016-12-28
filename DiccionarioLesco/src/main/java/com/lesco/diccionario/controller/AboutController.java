package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles all the registry related operations
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/acerca")
public class AboutController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(AboutController.class);
	
	
	/**
	 * University of Costa Rica detail page
	 * 
	 * @return
	 */
	@RequestMapping("/ucr")
	public ModelAndView ucr() {
		
		logger.debug("AboutController - ucr() - Start");
 
		ModelAndView mv = new ModelAndView("/about/ucr");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("AboutController - ucr() - End");
		
		return mv;
	}
	
	/**
	 * Escuela de ciencias de la computación e informática detail page
	 * 
	 * @return
	 */
	@RequestMapping("/ecci")
	public ModelAndView ecci() {
		
		logger.debug("AboutController - ecci() - Start");
 
		ModelAndView mv = new ModelAndView("/about/ecci");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("AboutController - ecci() - End");
		
		return mv;
	}
	
	
	/**
	 * LESCO detail page
	 * 
	 * @return
	 */
	@RequestMapping("/lesco")
	public ModelAndView lesco() {
		
		logger.debug("AboutController - lesco() - Start");
 
		ModelAndView mv = new ModelAndView("/about/lesco");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("AboutController - lesco() - End");
		
		return mv;
	}
//	
//
//	@Autowired
//	private UserDAO userDAO;
//	
//	@Autowired
//	private SHAEncryption shaEncryption;
//	
//	/**
//	 * Service that registers the user into the site
//	 * Type: Json POST method
//	 * 
//	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
//	 */
//	@RequestMapping(value= "/agregarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody AjaxResponseBody agregarUsuario(@RequestBody RegisterForm registerForm){
//		
//		logger.debug("RegisterController - agregarUsuario() - Start");
//		
//		AjaxResponseBody response = new AjaxResponseBody();
//
//		//Saves the user to the database
//		String resultadoSalvar= salvarUsuario(registerForm);
//		
//		//Response toggle based on the save return
//		if("Success".equals(resultadoSalvar)){
//			response.setCode("000");
//			response.setMessage("Success");
//		}else{
//			response.setCode("999");
//			response.setMessage("Failure");
//		}
//		
//		logger.debug("RegisterController - agregarUsuario() - End");
//		
//		return response;
//	}
//	
//	
//	/**
//	 * 
//	 * Verifies is the userName entered already exists in the database
//	 * 
//	 * Type: Json POST method
//	 * 
//	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
//	 */
//	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody RegisterForm registerForm){
//		
//		AjaxResponseBody result = new AjaxResponseBody();
//		
//		logger.debug("RegisterController - verificarUsuario() - Start");
//		
//		//Validate input
//		if(registerForm.getUserName() != null && registerForm.getUserName().length() != 0){
//			
//			//Checks if the input user name already exists in the database
//			if(userDAO.checkUserName(registerForm.getUserName().trim()) == false){			
//				result.setMessage("Sucess");
//				result.setCode("000");
//			}else{
//				result.setMessage("The user already exists");
//				result.setCode("001");
//			}
//		}else{
//			result.setMessage("Failure");
//			result.setCode("001");
//		}
//		
//		logger.debug("RegisterController - verificarUsuario() - End");
//		
//		return result;
//	}
//	
//	/**
//	 * 
//	 * Verifies is the emailAddress entered already exists in the database
//	 * 
//	 * Type: Json POST method
//	 * 
//	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
//	 */
//	@RequestMapping(value= "/verificarCorreo", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody AjaxResponseBody verificarCorreo(@RequestBody RegisterForm registerForm){
//		
//		AjaxResponseBody result = new AjaxResponseBody();
//		
//		logger.debug("RegisterController - verificarCorreo() - Start");
//		
//		//Validate input
//		if(registerForm.getEmailAddress() != null && registerForm.getEmailAddress().length() != 0){
//			
//			//Checks if the input user name already exists in the database
//			if(userDAO.checkEmailAddress(registerForm.getEmailAddress().trim()) == false){			
//				result.setMessage("Sucess");
//				result.setCode("000");
//			}else{
//				result.setMessage("The user already exists");
//				result.setCode("001");
//			}
//		}else{
//			result.setMessage("Failure");
//			result.setCode("001");
//		}
//		
//		logger.debug("RegisterController - verificarCorreo() - End");
//		
//		return result;
//	}
//	
//	
//	/**
//	 * Stores the new user into the database
//	 * 
//	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
//	 */
//	private String salvarUsuario(RegisterForm registerForm){
//		
//		//Validates that all values come from the form
//		if(registerForm.getUserName() != null && registerForm.getEmailAddress() != null && registerForm.getPassword() != null && 
//				registerForm.getPasswordConfirmation() != null	&& registerForm.getBirthDate() != null && registerForm.getTermsAndConditions() != null){
//					
//			//Checks if the userName already exists
//			//TODO Add the validation of the email
//			if(userDAO.checkUserName(registerForm.getUserName()) == null){
//			
//			//Get unique random salt which will be used to encryp the user password
//			byte[] salt= SHAEncryption.getSalt();
//			
//			//New Profile Detail
//			ProfileDetail profileDetail = new ProfileDetail();
//			profileDetail.setBirthDate(registerForm.getBirthDate());
//			profileDetail.setTermsAndConditions(registerForm.getTermsAndConditions());
//			profileDetail.setEmail(registerForm.getEmailAddress());
//			
//			//New User Profile
//			UserProfile userProfile = new UserProfile();
//			userProfile.setSalt(salt);
//			userProfile.setUserName(registerForm.getUserName());
//			userProfile.setUserPassword(shaEncryption.getHashedPassword(registerForm.getPassword(), salt));
//			
//			//Because this two instances have a one-to-one relationship, this needs to be done
//			userProfile.setProfileDetail(profileDetail);
//			profileDetail.setUserProfile(userProfile);
//			
//			//This saves both, the User Profile and the Profile Detail instances into the DB
//			userDAO.save(userProfile);
//			
//			//If I wanted to get the ID, I'd have to do something like:
//			//category.getId();
//			}
//			return "Success";
//		}else{
//			return"Failure";
//		}
//	}
}
