package com.lesco.diccionario.controller;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.dao.WordDAO;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.Word;

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
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private WordDAO wordDAO;
	
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
		
		//Get all the categories
		List<Category> listCategories = categoryDAO.list();
		
		//Get all the categories
		List<Word> listWords = wordDAO.list();
		
		//Get random video
		//TODO
		
		mv.addObject("randomWord", getRandomWord(listWords));
		 
		mv.addObject("listCategories", listCategories);
		mv.addObject("listWords", listWords);
		
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
	
	/**
	 * Add term page
	 * 
	 * @return
	 */
	@RequestMapping("/agregar")
	public ModelAndView agregar() {
		
		logger.debug("LescoController - agregar() - Start");
 
		ModelAndView mv = new ModelAndView("addTerm");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		List<Category> listCategories = categoryDAO.list();
		 
		mv.addObject("listCategories", listCategories);
		
		logger.debug("LescoController - agregar() - End");
		
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
	
	
	/*** Private methods ***/
	
	@SuppressWarnings("unused")
	private Word getRandomWord(List<Word> listWords){

		int START = 1;
	    int END = listWords.size();
	    Random random = new Random();
		
		Integer randomNumber = getRandomInteger(START, END, random);
		
		Word randomWord = listWords.get(randomNumber);
		
		return randomWord;
	}
	
	private static int getRandomInteger(int aStart, int aEnd, Random aRandom){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    //log("Generated : " + randomNumber);
	    
	    return randomNumber;
	  }
	
	
	
	
}
