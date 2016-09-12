package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LescoController {
	
	private static final Logger logger = Logger.getLogger(Principal.class);

	@RequestMapping("/lesco")
	public ModelAndView lesco() {
		
		logger.debug("LescoController - lesco() - Starting method");
 
		ModelAndView mv = new ModelAndView("lesco");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/testTemplate")
	public ModelAndView lescoTestTemplate() {
		
		logger.debug("LescoController - lescoTestTemplate() - Starting method");
 
		ModelAndView mv = new ModelAndView("testTemplate");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		return mv;
	}
	
}
