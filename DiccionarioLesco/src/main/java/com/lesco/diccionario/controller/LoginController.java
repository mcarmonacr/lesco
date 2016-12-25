package com.lesco.diccionario.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.LoginForm;



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

	/**
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/iniciarSesion", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody iniciarSesion(@RequestBody LoginForm loginForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("RegisterController - agregarUsuario() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		//Create user session
		HttpSession session = request.getSession();
		session.setAttribute("userEmail", loginForm.getEmailAddress().toString());
		
		//setting session to expiry in 15 mins
		session.setMaxInactiveInterval(15*60);
		
		Cookie cookie = new Cookie("JSESSIONID",session.getId().toString());
		cookie.setMaxAge(15*60); //1 hour
		response.addCookie(cookie);

		//Saves the user to the database
		//String resultadoSalvar= salvarUsuario(loginForm);
		
		//Response toggle based on the save return
		ajaxResponse.setCode("000");
		ajaxResponse.setMessage("Success");
		
		
		logger.debug("RegisterController - agregarUsuario() - End");
		
		return ajaxResponse;
	}
	
}
