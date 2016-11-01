package com.lesco.diccionario.controller;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.dao.CityDAO;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.City;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.CategoryForm;

/**
 * Admin class
 * Will handle all the operations that can be performed by users with administrative privileges.
 * 
 * @author m.carmona.dinarte
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	//AdminController Log4J class logger instance
	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * Home page
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView admin() {
		
		logger.debug("AdminController - admin() - Start of the method");
		
		ModelAndView mv = new ModelAndView("admin");
		
		//Get all the categories
		List<Category> listCategories = categoryDAO.list();
		 
		mv.addObject("listCategories", listCategories);
		
		logger.debug("AdminController - admin() - End of the method");
		
		return mv;
	}
	
	
	/**
	 * Adds a not existing category to the database
	 * 
	 * @param categoryForm
	 */
	@RequestMapping(value= "/agregarCategoria", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarCategoria(@RequestBody CategoryForm categoryForm){
		
		//Generic Response Body for all Ajax request
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("AdminController - agregarCategoria() - Starting method");
		
		if(categoryForm.getCategoryName() != null){
			System.out.println("Form text: " + categoryForm.getCategoryName());
			
			//Checks if the category already exists
			if(categoryDAO.findByCategoryName(categoryForm.getCategoryName()) == null){
				Category category = new Category();
				category.setCategoryName(categoryForm.getCategoryName());
				categoryDAO.save(category);
				
				//Si quisiera obtener el ID nada más tendría que hacer:
				//category.getId();
			}

			
			result.setMessage("Sucess");
		}else{
			result.setMessage("Failure");
		}

		return result;
	}
	
	
	
	
}
