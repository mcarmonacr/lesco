package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RegisterForm;
import com.lesco.diccionario.utils.SHAEncryption;

/**
 * Handles all the legal related pages
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/legal")
public class FooterController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(FooterController.class);
	
	/**
	 * Terms and conditions detail page
	 * 
	 * @return
	 */
	@RequestMapping("/terminos-de-servicio")
	public ModelAndView terminosDeServicio() {
		
		logger.debug("FooterController - terminosDeServicio() - Start");
 
		ModelAndView mv = new ModelAndView("/legal/termsAndConditions");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("FooterController - terminosDeServicio() - End");
		
		return mv;
	}
	
	
	/**
	 * Privacy detail page
	 * 
	 * @return
	 */
	@RequestMapping("/privacidad")
	public ModelAndView privacidad() {
		
		logger.debug("FooterController - privacidad() - Start");
 
		ModelAndView mv = new ModelAndView("/legal/privacy");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("FooterController - privacidad() - End");
		
		return mv;
	}
}
