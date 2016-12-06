package com.lesco.diccionario.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.model.Video;
import com.lesco.diccionario.model.Word;
import com.lesco.diccionario.pojo.AddTermForm;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RegisterForm;
import com.lesco.diccionario.utils.SHAEncryption;

/**
 * Handles all the terms related operations
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/termino")
public class TermnsController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(TermnsController.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SHAEncryption shaEncryption;
	
	/**
	 * Service that stores a new term into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: wordName, categoryName, definition, explanation ,example, youtubeType, fileType, videoURL, filePath.
	 * 
	 * 
	 */
	@RequestMapping(value= "/agregarTermino", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarTermino(@RequestBody AddTermForm addTermForm, HttpServletRequest request, 
	        HttpServletResponse response){
		
		logger.debug("TermnsController - agregarTermino() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();

		//Saves the user to the database
		String resultadoSalvar= salvarTermino(addTermForm, request);
		
		//Response toggle based on the save return
		if("Success".equals(resultadoSalvar)){
			ajaxResponse.setCode("000");
			ajaxResponse.setMessage("Success");
		}else{
			ajaxResponse.setCode("999");
			ajaxResponse.setMessage("Failure");
		}
		
		logger.debug("TermnsController - agregarTermino() - End");
		
		return ajaxResponse;
	}
	
	
	/**
	 * 
	 * Verifies is the userName entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/verificarUsuario", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarUsuario(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarUsuario() - Start");
		
		//Validate input
		if(registerForm.getUserName() != null && registerForm.getUserName().length() != 0){
			
			//Checks if the input user name already exists in the database
			if(userDAO.checkUserName(registerForm.getUserName().trim()) == false){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		}else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarUsuario() - End");
		
		return result;
	}
	
	/**
	 * 
	 * Verifies is the emailAddress entered already exists in the database
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/verificarCorreo", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody verificarCorreo(@RequestBody RegisterForm registerForm){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - verificarCorreo() - Start");
		
		//Validate input
		if(registerForm.getEmailAddress() != null && registerForm.getEmailAddress().length() != 0){
			
			//Checks if the input user name already exists in the database
			if(userDAO.checkEmailAddress(registerForm.getEmailAddress().trim()) == false){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		}else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarCorreo() - End");
		
		return result;
	}
	
	
	/**
	 * Stores the new user into the database
	 * 
	 * @param registerForm. Contains fields: wordName, categoryName, definition, explanation ,example, youtubeType, fileType, videoURL, filePath.
	 */
	private String salvarTermino(AddTermForm addTermForm, HttpServletRequest request){
		
		//Validates that all values come from the form
		if(addTermForm.getWordName() != null && addTermForm.getCategoryName() != null && addTermForm.getDefinition() != null && 
				addTermForm.getExplanation() != null	&& addTermForm.getExample() != null && addTermForm.getYoutubeType() != null
				&& addTermForm.getFileType() != null && addTermForm.getVideoURL() != null && addTermForm.getFilePath() != null){
					
			
			//Get user session
			HttpSession session = request.getSession();
			
			//Get the current logged in user emailAddress
			String userEmail = session.getAttribute("userEmail").toString();
			
			ProfileDetail profileDetail = userDAO.findByEmailAddress(userEmail);
			
			UserProfile userProfile = profileDetail.getUserProfile();
			
			//TODO: Complete the remaining fields || Add the remaining entities relationships
			//New Word
			Word word = new Word();
			word.setWordName(addTermForm.getWordName());
			
			//New Video
			Video video = new Video();
			video.setWord(word);
			
			Category category = categoryDAO.findByCategoryName(addTermForm.getCategoryName());
			
			
			word.setCategory(category);
			word.setVideo(video);
			
			Set<Word> words = new HashSet<Word>();
			words.add(word);
			
			category.setWords(words);
			userProfile.setWords(words);
			
			//Saves the new entities Word and Video, linked to the existing UserProfile and Category
			userDAO.save(userProfile);
			
//			//New Profile Detail
//			ProfileDetail profileDetail = new ProfileDetail();
//			profileDetail.setBirthDate(addTermForm.getBirthDate());
//			profileDetail.setTermsAndConditions(addTermForm.getTermsAndConditions());
//			profileDetail.setEmail(addTermForm.getEmailAddress());
//			
//			//New User Profile
//			UserProfile userProfile = new UserProfile();
//			userProfile.setSalt(salt);
//			userProfile.setUserName(addTermForm.getUserName());
//			userProfile.setUserPassword(shaEncryption.getHashedPassword(addTermForm.getPassword(), salt));
//			
//			//Because this two instances have a one-to-one relationship, this needs to be done
//			userProfile.setProfileDetail(profileDetail);
//			profileDetail.setUserProfile(userProfile);
//			
//			//This saves both, the User Profile and the Profile Detail instances into the DB
//			userDAO.save(userProfile);
			
			//If I wanted to get the ID, I'd have to do something like:
			//category.getId();
			
			return "Success";
		}else{
			return"Failure";
		}
	}
}
