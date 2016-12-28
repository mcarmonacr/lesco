package com.lesco.diccionario.controller;

import javax.servlet.http.Cookie;
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

import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.LoginForm;
import com.lesco.diccionario.utils.SHAEncryption;



/**
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

@Controller
@RequestMapping("/ingreso")
public class LoginController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private SHAEncryption shaEncryption;
	
	@Autowired
	private UserDAO userDAO;
	

	/**
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/iniciarSesion", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody iniciarSesion(@RequestBody LoginForm loginForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("RegisterController - iniciarSesion() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		//Verifies the login
		if(verifyUser(loginForm)){
			//Validation the session, creates a new one in case there isn't one already created
			verifySession(loginForm, request, response);
			
			//Response toggle based on the save return
			ajaxResponse.setCode("000");
			ajaxResponse.setMessage("Success");
			
		} else {
			//Response toggle based on the save return
			ajaxResponse.setCode("999");
			ajaxResponse.setMessage("Error");
		}
				
		logger.debug("RegisterController - iniciarSesion() - End");
		
		return ajaxResponse;
	}
	
	/**
	 * 
	 * 
	 * @param loginForm
	 * @return
	 */
	private Boolean verifyUser(LoginForm loginForm){
	
		ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(loginForm.getEmailAddress());
		
		ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
		
		UserProfile userProfile = ProfileDetailReference.getUserProfile();
		
		byte[] salt= userProfile.getSalt();
		
		String hashedPassword = shaEncryption.getHashedPassword(loginForm.getPassword(), salt);
		
		//If passwords match return success
		if (hashedPassword.equals(userProfile.getUserPassword())){
			return true;
		} else {
			return false;
		}
		
		//return true;

	}
	
	/**
	 * 
	 * 
	 * @param loginForm
	 * @param request
	 * @param response
	 */
	private void verifySession(LoginForm loginForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		//Create user session or get the current in case one already exists
		HttpSession session = request.getSession();
		session.setAttribute("userEmail", loginForm.getEmailAddress().toString());
		
		//setting session to expire in 15 minutes
		session.setMaxInactiveInterval(15*60);
		
		//Adds the JSESSIONID cookie to be able to maintain the same session
		Cookie cookie = new Cookie("JSESSIONID",session.getId().toString());
		cookie.setMaxAge(15*60); //15 minutes
		response.addCookie(cookie);
	}
}
