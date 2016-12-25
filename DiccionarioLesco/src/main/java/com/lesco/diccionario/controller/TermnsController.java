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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.dao.WordDAO;
import com.lesco.diccionario.helper.UploadVideo;
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
	private WordDAO wordDAO;
	
	@Autowired
	private SHAEncryption shaEncryption;
	
	@Autowired
	private UploadVideo uploadVideo;
	
	/**
	 * Service that stores a new term into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: wordName, categoryName, definition, explanation ,example, youtubeType, fileType, videoURL, filePath.
	 * 
	 * 
	 */
	@RequestMapping(value= "/agregarTermino", method = RequestMethod.POST/*, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE*/)
	public @ResponseBody AjaxResponseBody agregarTermino(@RequestPart(value = "data", required = false) AddTermForm addTermForm, @RequestPart(value = "video") MultipartFile videoFile, HttpServletRequest request, 
	        HttpServletResponse response){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		try{
			logger.debug("TermnsController - agregarTermino() - Start");
			
			
			
			ajaxResponse.setCode("000");
			ajaxResponse.setMessage("Success");
			
			//videoFile.getInputStream()
			

			//Saves the user to the database
			String resultadoSalvar= salvarTermino(addTermForm, videoFile, request);
			
			//Response toggle based on the save return
			if("Success".equals(resultadoSalvar)){
				ajaxResponse.setCode("000");
				ajaxResponse.setMessage("Success");
			}else{
				ajaxResponse.setCode("999");
				ajaxResponse.setMessage("Failure");
			}
		} catch (Exception e){
			logger.error("TermnsController - agregarTermino() - ERROR",e);
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
	private String salvarTermino(AddTermForm addTermForm, MultipartFile videoFile, HttpServletRequest request){
		
		//Validates that all values come from the form
		if(addTermForm.getWordName() != null && addTermForm.getCategoryName() != null && addTermForm.getDefinition() != null && 
				addTermForm.getExplanation() != null	&& addTermForm.getExample() != null /*&& addTermForm.getYoutubeType() != null
				&& addTermForm.getFileType() != null && addTermForm.getVideoURL() != null&& addTermForm.getFilePath() != null */){		
			
			//First step is to upload the video to Youtube
			//String youtubeVideoID= uploadVideo.upload(addTermForm, videoFile);
			String youtubeVideoID = "xy6IFAzuMSI";
			
			if(!youtubeVideoID.isEmpty()){
				//Get user session
				HttpSession session = request.getSession();
				
				//Get the current logged in user emailAddress
				String userEmail = session.getAttribute("userEmail").toString();
				
				//Obtain the User that belongs to the email
				ProfileDetail profileDetail = userDAO.findByEmailAddress(userEmail);
				UserProfile userProfile = profileDetail.getUserProfile();
				
				userProfile.setUserName("Test");
				
				//New Word
				Word word = new Word();
				word.setWordName(addTermForm.getWordName());
				//word.setCategory(addTermForm.getCategoryName());
				word.setDefinition(addTermForm.getDefinition());
				word.setExplanation(addTermForm.getExplanation());
				word.setExample(addTermForm.getExample());
				word.setNumberOfVisits(0);
				
				//TODO
				//New Video
				Video video = new Video();
				video.setYoutubeVideoID(youtubeVideoID);
				
				//Relationship references
				video.setWord(word);
				word.setVideo(video);
				
				Set<Word> words = new HashSet<Word>();
				words.add(word);
				
				//Relationship references
				userProfile.setWords(words);
				word.setUserProfile(userProfile);
				
				//Get the category
				if(addTermForm.getCategoryName().trim().length() != 0){
					Category category = categoryDAO.findByCategoryName(addTermForm.getCategoryName());
					
					//Relationship references
					category.setWords(words);
					word.setCategory(category);
				}
				
				//Saves the new entities Word and Video, linked to the existing UserProfile and Category. 
				//userDAO.update(userProfile);
				wordDAO.save(word);
				
			} else {
				return"Failure";
			}
			return "Success";
		}else{
			return"Failure";
		}
	}
}
