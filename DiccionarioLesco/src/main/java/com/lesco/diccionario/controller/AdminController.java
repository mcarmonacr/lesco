package com.lesco.diccionario.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.CategoryForm;

/**
 * Admin class
 * Will handle all the operations that can be performed by users with administrative privileges.
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * Administrator home page 
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView admin() {
		
		logger.debug("AdminController - admin() - Start");
		
		ModelAndView mv = new ModelAndView("admin");
		
		//Get all the categories
		List<Category> listCategories = categoryDAO.list();
		 
		mv.addObject("listCategories", listCategories);
		
		logger.debug("AdminController - admin() - End");
		
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
		
		logger.debug("AdminController - agregarCategoria() - Start");
		
		//Check if the category name coming form the form is not null
		if(categoryForm.getCategoryName() != null){
			System.out.println("Form text: " + categoryForm.getCategoryName());
			
			//Checks if the category already exists
			if(categoryDAO.findByCategoryName(categoryForm.getCategoryName()) == null){
				Category category = new Category();
				category.setCategoryName(categoryForm.getCategoryName());
				categoryDAO.save(category);
				
				//If I wanted to get the ID from the category, I'd have to do something like:
				//category.getId();
			}
			result.setMessage("Sucess");
		}else{
			result.setMessage("Failure");
		}
		
		logger.debug("AdminController - agregarCategoria() - End");
		
		return result;
	}
	
	@RequestMapping(value= "/eliminarCategoria", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody eliminarCategoria(@RequestBody Map<String, String> json){
		
		//Generic Response Body for all Ajax request
		AjaxResponseBody result = new AjaxResponseBody();
		result.setMessage("Failure");
		logger.debug("AdminController - eliminarCategoria() - Start");
		
		//Check if the category name coming form the form is not null
		if(json.get("categoryId") != null && !json.get("categoryId").isEmpty()){
			try{
				//Checks if the category already exists
				if(categoryDAO.deleteById(Integer.valueOf(json.get("categoryId")))){
					result.setMessage("Sucess");
				}else{
					result.setMessage("Failure");
				}
			} catch (Exception e) {
				logger.error("There was an error processing the request", e);
			}
		}			
		logger.debug("AdminController - eliminarCategoria() - End");
		
		return result;
	}
}

