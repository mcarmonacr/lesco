package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LescoController {
	
	private static final Logger logger = Logger.getLogger(Principal.class);
	
	/**
	 * Home page
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView lesco() {
		
		logger.debug("LescoController - lesco() - Starting method");
 
		ModelAndView mv = new ModelAndView("home");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
	/**
	 * Home page
	 * 
	 * @return
	 */
	@RequestMapping("/registrarse")
	public ModelAndView registrarse() {
		
		logger.debug("LescoController - registro() - Starting method");
 
		ModelAndView mv = new ModelAndView("register");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
	
	/**
	 * Contact page
	 * 
	 * @return
	 */
	@RequestMapping("/contacto")
	public ModelAndView contacto() {
		
		logger.debug("LescoController - contacto() - Starting method");
 
		ModelAndView mv = new ModelAndView("contact");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
	
	/**
	 * About page
	 * 
	 * @return
	 */
	@RequestMapping("/acerca")
	public ModelAndView acerca() {
		
		logger.debug("LescoController - acerca() - Starting method");
 
		ModelAndView mv = new ModelAndView("about");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
}
