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
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.City;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.CategoryForm;
import com.lesco.diccionario.pojo.RegisterForm;

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
	private UserDAO userDAO;
	
	/**
	 * Home page
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView admin() {
		
		logger.debug("AdminController - admin() - Starting method");
		
//		List<Category> listCategories = userDAO.list();
//		
//		System.out.println("List of cities " + listCategories);
 
		ModelAndView mv = new ModelAndView("admin");
		
//		mv.addObject("listCategories", listCategories);
		
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
	 * @param registerForm
	 */
	@RequestMapping(value= "/agregarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarUsuario(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("AdminController - agregarCategoria() - Starting method");
		
		if(registerForm.getUserName() != null){
			System.out.println("Form text: " + registerForm.getUserName());
			
			//Checks if the category already exists
			if(userDAO.findByUserName(registerForm.getUserName()) == null){
				//Category category = new Category();
				//category.setCategoryName(categoryForm.getCategoryName());
				//userDAO.save(category);
				
				//Si quisiera obtener el ID nada más tendría que hacer:
				//category.getId();
			}

			
			result.setMessage("Sucess");
		}else{
			result.setMessage("Failure");
		}

		return result;
	}
	
	
	/**
	 * Verifica si el nombre de usuario ya se encuentra en la base de datos
	 * 
	 * Json POST method
	 * 
	 * @param registerForm
	 */
	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("AdminController - agregarCategoria() - Starting method");
		
		if(registerForm.getUserName() != null){
			System.out.println("Form text: " + registerForm.getUserName());
			
			//Checks if the category already exists
			if(userDAO.findByUserName(registerForm.getUserName().trim()) == null){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("Failure");
				result.setCode("001");
			}
		}else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		return result;
	}
	
	
	
	
	
	
}
