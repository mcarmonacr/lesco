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
 * Handles all the login related activities
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
	 * Service that logins the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: emailAddress, password
	 */
	@RequestMapping(value= "/iniciarSesion", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody iniciarSesion(@RequestBody LoginForm loginForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("LoginController - iniciarSesion() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		try{
			//Verifies the login. Response toggle based on the save return
			if(verifyUser(loginForm)){
				
				//Validation the session, creates a new one in case there isn't one already created
				verifySession(loginForm, request, response);

				ajaxResponse.setCode("000");
				ajaxResponse.setMessage("Success");
			} else {
				ajaxResponse.setCode("999");
				ajaxResponse.setMessage("Error");
			}
		} catch (Exception e){
			logger.error("LoginController - iniciarSesion() - Error", e);
		}
	
		logger.debug("LoginController - iniciarSesion() - End");
		
		return ajaxResponse;
	}
	
	/**
	 * Service that log users out the site
	 * 
	 * @param request
	 * @param response
	 * @return AjaxResponseBody
	 */
	@RequestMapping(value= "/finalizarSesion", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody finalizarSesion(HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("LoginController - finalizarSesion() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		try{
			//Invalidates the user session. Response toggle based on the save return
			if(endUserSession(request, response)){
				ajaxResponse.setCode("000");
				ajaxResponse.setMessage("Success");
			} else {
				ajaxResponse.setCode("999");
				ajaxResponse.setMessage("Error");
			}
		} catch (Exception e){
			logger.error("LoginController - finalizarSesion() - Error", e);
		}
		
		logger.debug("LoginController - finalizarSesion() - End");
		
		return ajaxResponse;
	}
	
	/**
	 * Ends the user session by invalidating the session and deleting the user's cookie.
	 * 
	 * @param request
	 * @param response
	 * @return Boolean
	 */
	private Boolean endUserSession(HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("LoginController - endUserSession() - Start");
		
		try{
			//Gets the current user's session
			HttpSession session = request.getSession();
			
			//Invalidates the current session
			session.invalidate();
			
			//Adds a new null JSESSIONID cookie to invalidate the current valid cookie
			Cookie cookie = new Cookie("JSESSIONID",null);
			cookie.setMaxAge(0); //0 minutes
			response.addCookie(cookie);
		}catch (Exception e){
			logger.error("LoginController - endUserSession() - Error", e);
		}

		logger.debug("LoginController - endUserSession() - End");
		
		return true;
	}
	
	/**
	 * Verifies the user's login information by matching the form with the database values
	 * 
	 * @param loginForm
	 * @return Boolean
	 */
	private Boolean verifyUser(LoginForm loginForm){
	
		logger.debug("LoginController - verifyUser() - Start");
		
		//Value to be returned
		Boolean isUserdVerfied = false;
		
		try{
			//Get the user's profile detail
			ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(loginForm.getEmailAddress());
			
			if(profileDetailQuery != null){
				//Get user's profile detail hibernate's reference
				ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
				
				if(ProfileDetailReference != null){
					//Get user's profile using the user's profile detail object
					UserProfile userProfile = ProfileDetailReference.getUserProfile();
					
					//Get original user's sal value. This value is needed to encrypt the user's input and see if it matches the value in the Database.
					byte[] salt= userProfile.getSalt();
					
					//Get hashed passwrod from the user's input
					String hashedPassword = shaEncryption.getHashedPassword(loginForm.getPassword(), salt);
					
					//If passwords match, return success
					if (hashedPassword.equals(userProfile.getUserPassword())){
						isUserdVerfied= true;
					} else {
						isUserdVerfied= false;
					}
				}
			}
		}catch(Exception e){
			logger.error("LoginController - verifyUser() - Error", e);
		}

		logger.debug("LoginController - verifyUser() - End");
		
		return isUserdVerfied;
	}
	
	/**
	 * Verifies the current user's session
	 * 
	 * @param loginForm
	 * @param request
	 * @param response
	 */
	private void verifySession(LoginForm loginForm, HttpServletRequest request, 
	        HttpServletResponse response){
		logger.debug("LoginController - verifySession() - Start");
		
		try{
			//Create user session or get the current in case one already exists
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", loginForm.getEmailAddress().toString());
			
			//setting session to expire in 15 minutes
			session.setMaxInactiveInterval(15*60);
			
			//Adds the JSESSIONID cookie to be able to maintain the same session
			Cookie cookie = new Cookie("JSESSIONID",session.getId().toString());
			cookie.setMaxAge(15*60); //15 minutes
			response.addCookie(cookie);	
		}catch(Exception e){
			logger.error("LoginController - verifySession() - Error", e);
		}

		logger.debug("LoginController - verifySession() - End");
	}
}
