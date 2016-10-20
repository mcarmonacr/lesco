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
import com.lesco.diccionario.modelo.Category;
import com.lesco.diccionario.modelo.City;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.CategoryForm;

/**
 * 
 * 
 * 
 * @author m.carmona.dinarte
 *
 */
@Controller
@RequestMapping("/registro")
public class RegisterController {
	
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * Home page
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView admin() {
		
		logger.debug("AdminController - admin() - Starting method");
		
		List<Category> listCategories = categoryDAO.list();
		
		System.out.println("List of cities " + listCategories);
 
		ModelAndView mv = new ModelAndView("admin");
		
		mv.addObject("listCategories", listCategories);
		//mv.addObject("name", name);
		return mv;
	}
	
	
	
	/**
	 * Regular POST method 
	 * 
	 * @param categoryForm
	 */
//	@RequestMapping(value= "/agregarCategoria", method = RequestMethod.POST)
//	public void agregarCategoria(@ModelAttribute("categoryForm") CategoryForm categoryForm){
//		
//		logger.debug("AdminController - agregarCategoria() - Starting method");
//		
//		System.out.println("Form text: " + categoryForm.getCategoryName());
//		
//	}
	
	
	/**
	 * Json POST method
	 * 
	 * @param categoryForm
	 */
	@RequestMapping(value= "/agregarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarUsuario(@RequestBody CategoryForm categoryForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("AdminController - agregarCategoria() - Starting method");
		
		if(categoryForm.getCategoryName() != null){
			System.out.println("Form text: " + categoryForm.getCategoryName());
			
			//Checks if the category already exists
			if(categoryDAO.findByCategoryName(categoryForm.getCategoryName()) == null){
				Category category = new Category();
				category.setCategoryName(categoryForm.getCategoryName());
				categoryDAO.save(category);
				
				//Si quisiera obtener el ID nada m�s tendr�a que hacer:
				//category.getId();
			}

			
			result.setMessage("Sucess");
		}else{
			result.setMessage("Failure");
		}

		return result;
	}
	
	
	/**
	 * Verifica si el usuario ya se encuentra en la base de datos
	 * 
	 * Json POST method
	 * 
	 * @param categoryForm
	 */
	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody CategoryForm categoryForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("AdminController - agregarCategoria() - Starting method");
		
		if(categoryForm.getCategoryName() != null){
			System.out.println("Form text: " + categoryForm.getCategoryName());
			
			//Checks if the category already exists
			if(categoryDAO.findByCategoryName(categoryForm.getCategoryName()) == null){
				Category category = new Category();
				category.setCategoryName(categoryForm.getCategoryName());
				categoryDAO.save(category);
				
				//Si quisiera obtener el ID nada m�s tendr�a que hacer:
				//category.getId();
			}

			
			result.setMessage("Sucess");
		}else{
			result.setMessage("Failure");
		}

		return result;
	}
	
	
	
	
}
