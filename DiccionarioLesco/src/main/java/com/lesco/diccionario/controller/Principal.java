package com.lesco.diccionario.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lesco.diccionario.dao.CityDAO;
import com.lesco.diccionario.model.City;


@Controller
public class Principal {

	String message = "Welcome to Spring MVC!";
	
	private static final Logger logger = Logger.getLogger(Principal.class);
	
	
	@Autowired
	private CityDAO cityDAO;
	
	@RequestMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
		//logs exception
		logger.error("This is Error message", new Exception("Testing"));
 
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/helloworld")
	public ModelAndView prin(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
		//logs exception
		logger.error("This is Error message", new Exception("Testing"));
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/principal")
	public ModelAndView principal(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
		List<City> listCities = cityDAO.list();
		
		System.out.println("List of cities " + listCities);
		
		//logs exception
		logger.error("This is Error message", new Exception("Testing"));
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
		
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
		//logs exception
		logger.error("This is Error message", new Exception("Testing"));
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
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
