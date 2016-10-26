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
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.CategoryForm;
import com.lesco.diccionario.pojo.RegisterForm;
import com.lesco.diccionario.utils.SHAEncryption;

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
	
	@Autowired
	private SHAEncryption shaEncryption;
	
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
		
		//shaEncryption.getHashedPassword("password");
		
		
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
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/agregarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarUsuario(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody respuesta = new AjaxResponseBody();
		
		logger.debug("RegisterController - agregarCategoria() - Method start");

	
		String resultadoSalvar= salvarUsuario(registerForm);
		
		if("Sucess".equals(resultadoSalvar)){
			
		}else{
			
		}
		

		return respuesta;
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
	
	
	/**
	 * Guarda el nuevo usuario en la base de datos
	 * 
	 * @param registerForm
	 */
	private String salvarUsuario(RegisterForm registerForm){
		
		//Validates that all values come from the form
				if(registerForm.getUserName() != null && registerForm.getEmailAddress() != null && registerForm.getPassword() != null && 
						registerForm.getPasswordConfirmation() != null	&& registerForm.getBirthDate() != null && registerForm.getTermsAndConditions() != null){
					
					System.out.println("Form text: " + registerForm.getUserName());
					
					//Checks if the userName already exists
					//TODO Add the validation of the email
					if(userDAO.findByUserName(registerForm.getUserName()) == null){
						//Category category = new Category();
						//category.setCategoryName(categoryForm.getCategoryName());
						//userDAO.save(category);
						
						//Get unique random salt which will be used to encryp the user password
						byte[] salt= SHAEncryption.getSalt();
						
						ProfileDetail profileDetail = new ProfileDetail();
						profileDetail.setBirthDate(registerForm.getBirthDate());
						profileDetail.setTermsAndConditions(registerForm.getTermsAndConditions());
						profileDetail.setEmail(registerForm.getEmailAddress());
						
						UserProfile userProfile = new UserProfile();
						
						userProfile.setSalt(salt);
						userProfile.setUserName(registerForm.getUserName());
						userProfile.setUserPassword(shaEncryption.getHashedPassword(registerForm.getPassword(), salt));
						
						userDAO.save(userProfile);
						
						
						
						//Si quisiera obtener el ID nada más tendría que hacer:
						//category.getId();
					}

					
					return "Sucess";
				}else{
					return"Failure";
				}
	}
	
	
	
	
	
	
}
