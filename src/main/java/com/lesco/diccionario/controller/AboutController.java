package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles all the about section related operations
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
	 * @return modelAndView
	 */
	@RequestMapping("/ucr")
	public ModelAndView ucr() {
		
		logger.debug("AboutController - ucr() - Start");
 
		ModelAndView mv = new ModelAndView("/about/ucr");
		
		logger.debug("AboutController - ucr() - End");
		
		return mv;
	}
	
	/**
	 * Escuela de Ciencias de la Computación e Informática detail page
	 * 
	 * @return
	 */
	@RequestMapping("/ecci")
	public ModelAndView ecci() {
		
		logger.debug("AboutController - ecci() - Start");
 
		ModelAndView mv = new ModelAndView("/about/ecci");
		
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
		
		logger.debug("AboutController - lesco() - End");
		
		return mv;
	}
	
	/**
	 * Resources detail page
	 * 
	 * @return
	 */
	@RequestMapping("/recursos")
	public ModelAndView recursos() {
		
		logger.debug("AboutController - recursos() - Start");
 
		ModelAndView mv = new ModelAndView("/about/resources");
		
		logger.debug("AboutController - recursos() - End");
		
		return mv;
	}
}
