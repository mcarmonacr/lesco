package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
public class LescoController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(Principal.class);
	
	/**
	 * DiccioanrioLesco Home Page
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView diccionarioLesco() {
		
		logger.debug("LescoController - diccionarioLesco() - Start");
 
		ModelAndView mv = new ModelAndView("home");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - diccionarioLesco() - End");
		
		return mv;
	}
	
	/**
	 * Registry page
	 * 
	 * @return
	 */
	@RequestMapping("/registrarse")
	public ModelAndView registrarse() {
		
		logger.debug("LescoController - registrarse() - Start");
 
		ModelAndView mv = new ModelAndView("register");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - registrarse() - End");
		
		return mv;
	}
	
	
	/**
	 * Contact page
	 * 
	 * @return
	 */
	@RequestMapping("/contacto")
	public ModelAndView contacto() {
		
		logger.debug("LescoController - contacto() - Start");
 
		ModelAndView mv = new ModelAndView("contact");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - contacto() - End");
		
		return mv;
	}
	
	
	/**
	 * About page
	 * 
	 * @return
	 */
	@RequestMapping("/acerca")
	public ModelAndView acerca() {
		
		logger.debug("LescoController - acerca() - Start");
 
		ModelAndView mv = new ModelAndView("about");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - acerca() - End");
		
		return mv;
	}
	
}
