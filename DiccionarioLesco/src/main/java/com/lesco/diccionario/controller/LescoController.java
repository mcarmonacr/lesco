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
	
}
