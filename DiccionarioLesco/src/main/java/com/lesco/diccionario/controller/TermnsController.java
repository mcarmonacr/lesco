package com.lesco.diccionario.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.lesco.diccionario.helper.YoutubeHelper;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.model.Video;
import com.lesco.diccionario.model.Word;
import com.lesco.diccionario.pojo.AddTermForm;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RegisterForm;

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
	private YoutubeHelper youtubeHelper;
	
	/**
	 * Service that stores a new term into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: wordName, categoryName, definition, explanation ,example, youtubeType, fileType, videoURL, filePath.
	 * 
	 * 
	 */
	@RequestMapping(value= "/agregarTermino", method = RequestMethod.POST/*, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE*/)
	public @ResponseBody AjaxResponseBody agregarTermino(@RequestPart(value = "data", required = false) AddTermForm addTermForm, @RequestPart(value = "video") MultipartFile videoFile, 
			@RequestPart(value = "definitionVideo") MultipartFile definitionVideoFile, @RequestPart(value = "explanationVideo") MultipartFile explanationVideoFile, 
			@RequestPart(value = "exampleVideo") MultipartFile exampleVideoFile,
			HttpServletRequest request, HttpServletResponse response){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		try{
			logger.debug("TermnsController - agregarTermino() - Start");

			ajaxResponse.setCode("000");
			ajaxResponse.setMessage("Success");

			//Saves the user to the database
			String resultadoSalvar= salvarTermino(addTermForm, videoFile, definitionVideoFile, explanationVideoFile, exampleVideoFile, request);

			//String resultadoSalvar= "success";
			
			//Response toggle based on the save return
			if("Success".equals(resultadoSalvar)){
				ajaxResponse.setCode("000");
				ajaxResponse.setMessage("Success");
			}else{
				ajaxResponse.setCode("999");
				ajaxResponse.setMessage("Failure");
			}
		} catch (Exception e){
			logger.error("TermnsController - agregarTermino() - Error: ",e);
		}
		
		logger.debug("TermnsController - agregarTermino() - End");
		
		return ajaxResponse;
	}
	
	
	/**
	 * Get the word corresponding to the sent ID
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/obtenerTermino", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody obtenerTermino(@RequestBody Map<String, String> json){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - obtenerTermino() - Start");
		
		///Checks if the word id comes from the request
		if (json.get("wordId") != null){			
			Integer wordId = Integer.parseInt(json.get("wordId"));
			if(wordId != null){
				Word word= wordDAO.findById(wordId);
				
				//If the word is found in the database
				if(word != null){
					Map <String, Object> wordMap = new HashMap <String, Object> ();
					
					wordMap.put("wordId", wordId.toString());
					wordMap.put("wordName", word.getWordName());
					wordMap.put("definition", word.getDefinition());
					wordMap.put("explanation", word.getExplanation());
					wordMap.put("example", word.getExample());
					wordMap.put("numberOfVisits", word.getNumberOfVisits().toString());
					
					//TODO update mapping
					//wordMap.put("youtubeVideoID", word.getVideo().getYoutubeVideoID());
					
					result.setContent(wordMap);
					result.setMessage("Success");
					result.setCode("000");
				} else {
					result.setMessage("Could not find the word");
					result.setCode("001");
				}
			}
		} else{
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - obtenerTermino() - End");
		
		return result;
	}
	
	/**
	 * Gets all terms form the DB that match the search input pattern
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/obtenerListaTerminos", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody obtenerListaTerminos(@RequestBody Map<String, String> json){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RegisterController - obtenerListaTerminos() - Start");
		
		//Validate input
		if (json.get("termsInput") != null && !json.get("termsInput").isEmpty()){
			
			List<Word> wordsList = new ArrayList<Word>();
			
			//If there's a category then it's included in the search 
			if(json.get("categoryIdDiv") != null && !json.get("categoryIdDiv").isEmpty()){
				wordsList = wordDAO.findByPatternAndCategoryId(json.get("termsInput"), Integer.parseInt(json.get("categoryIdDiv"))); //wordDAO.list();
			} else {
				//Search the word regardless the category
				wordsList = wordDAO.findByPattern(json.get("termsInput"));
			}
			
			Map <String, Object> wordsMap = new HashMap <String, Object> ();
			
			// TODO process wordsMap in order to get only the list of words and its IDs 
			
			wordsMap.put("wordsList", processWordList(wordsList));
			result.setContent(wordsMap);
					
			//Checks if the input user name already exists in the database
			if(wordsList != null && !wordsList.isEmpty()){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("List is empty");
				result.setCode("001");
			}
		}
		logger.debug("RegisterController - obtenerListaTerminos() - End");
		
		return result;
	}
	
	
	
	/**
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
			if(userDAO.checkUserName(registerForm.getUserName().trim()) == false) {			
				result.setMessage("Sucess");
				result.setCode("000");
			} else {
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		} else {
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarUsuario() - End");
		
		return result;
	}
	
	/**
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
			if(userDAO.checkEmailAddress(registerForm.getEmailAddress().trim()) == false) {			
				result.setMessage("Sucess");
				result.setCode("000");
			} else {
				result.setMessage("The user already exists");
				result.setCode("001");
			}
		} else {
			result.setMessage("Failure");
			result.setCode("001");
		}
		
		logger.debug("RegisterController - verificarCorreo() - End");
		
		return result;
	}
	
	/**** Private Methods ****/ 
	
	/**
	 * Stores a new term in the database
	 * 
	 * @param addTermForm
	 * @param videoFile
	 * @param definitionVideoFile
	 * @param explanationVideoFile
	 * @param exampleVideoFile
	 * @param request
	 * @return operation state
	 */
	private String salvarTermino(AddTermForm addTermForm, MultipartFile videoFile, MultipartFile definitionVideoFile, MultipartFile explanationVideoFile, MultipartFile exampleVideoFile, HttpServletRequest request){
		
		logger.debug("RegisterController - salvarTermino() - Start");
		
		String result = "";
		
		try {
			//Validates that the required values come from the form
			if(addTermForm.getWordName() != null && videoFile != null){		
				
				//First step is to upload the video to Youtube
				//String youtubeVideoID= uploadVideo.upload(addTermForm, videoFile);
				//String youtubeVideoID = "xy6IFAzuMSI";
				//String youtubeVideoID = "X7PpGPOHVrA";
				
				//Words values
				String wordName = addTermForm.getWordName(); //At this point the variable most be different from null
				String definition = addTermForm.getDefinition() != null ? addTermForm.getDefinition() : "";
				String explanation = addTermForm.getExplanation() != null ? addTermForm.getExplanation() : "";
				String example = addTermForm.getExample() != null ? addTermForm.getExample() : "";
				
				//This video does not need validation on some values since its values are compulsory
				//String termYoutubeVideoID = youtubeHelper.uploadVideo(wordName+ "en Lenguaje de Señas Costarricense (LESCO)" , wordName+ "en Lenguaje de Señas Costarricense (LESCO)" , videoFile);
				
				String termYoutubeVideoID = "X7PpGPOHVrA";
				
				//Rest of the videos IDs
				String definitionYoutubeVideoID = "";
				String explanationYoutubeVideoID = "";
				String exampleYoutubeVideoID = "";
				
				if (definition != null && definitionVideoFile != null){
					//definitionYoutubeVideoID = youtubeHelper.uploadVideo("Definición en LESCO del término " + wordName, "Definición en LESCO del término: " + wordName + " - " + definition, definitionVideoFile);
				}
				
				if (explanation != null && explanationVideoFile != null){
					//explanationYoutubeVideoID = youtubeHelper.uploadVideo("Explicación en LESCO del término " + wordName, "Explicación en LESCO del término: " + wordName + " - " + explanation, explanationVideoFile);
				}
				
				if (example != null && exampleVideoFile != null){
					//exampleYoutubeVideoID = youtubeHelper.uploadVideo("Ejemplo en LESCO del término " + wordName, "Ejemplo en LESCO del término: " + wordName + " - " + example, exampleVideoFile);
				}
				
				//The term video is the only that is compulsory, the other ones are optional
				if(!termYoutubeVideoID.isEmpty()){
					//Get user session
					HttpSession session = request.getSession();
					
					//Get the current logged in user emailAddress
					String userEmail = session.getAttribute("userEmail").toString();
					
					//Obtain the User that belongs to the email
					ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
					
					ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
					
					UserProfile userProfile = ProfileDetailReference.getUserProfile();
					
					//New Word
					Word word = new Word();
					word.setWordName(wordName);
					//word.setCategory(addTermForm.getCategoryName());
					if(definition != null) word.setDefinition(definition);
					if(explanation != null) word.setExplanation(explanation);
					if(example != null) word.setExample(example);
					word.setNumberOfVisits(0);
					
					//TODO Update the Youtube video ID according to the 4 new video names
					//New Video
					Video video = new Video();
					//video.settermYouTubeVideoID(termYouTubeVideoID);
					video.setTermYoutubeVideoID(termYoutubeVideoID);
					
					//Set additional videos if available
					if(definitionYoutubeVideoID != null) {
						video.setDefinitionYoutubeVideoID(definitionYoutubeVideoID);
					}
					if(explanationYoutubeVideoID != null){
						video.setExampleYoutubeVideoID(exampleYoutubeVideoID);
					}
					if(exampleYoutubeVideoID != null){
						video.setExplanationYoutubeVideoID(explanationYoutubeVideoID);
					}
					
					//Relationship references
					video.setWord(word);
					word.setVideo(video);
					
					Set<Word> words = new HashSet<Word>();
					words.add(word);
					
					//Relationship references
					userProfile.setWords(words);
					word.setUserProfile(userProfile);
					
					//Get the category
					if(addTermForm.getCategoryName() != null && addTermForm.getCategoryName().trim().length() != 0){
						Category category = categoryDAO.findByCategoryName(addTermForm.getCategoryName());
						
						//Relationship references
						category.setWords(words);
						word.setCategory(category);
					}
					
					//Saves the new entities Word and Video, linked to the existing UserProfile and Category. 
					//userDAO.update(userProfile);
					wordDAO.save(word);
					
				} else {
					result= "Failure";
				}
				result= "Success";
			}else{
				result= "Failure";
			}
		} catch(Exception e) {
			logger.debug("RegisterController - salvarTermino() - Error: ", e);
		}

		logger.debug("RegisterController - salvarTermino() - End");
		
		return result;
	}
	
	/**
	 * Get the raw data and place the necessary data in the resulting map
	 * 
	 * @param wordList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List processWordList(List<Word> wordList){
		
		logger.debug("RegisterController - processWordList() - Start");
		
		List result = new ArrayList();
		
		//Processes the word's list and add the necessary data in the resulting map
		for(Word actualWord:wordList){
			Map actualWordMap = new HashMap();
			
			actualWordMap.put("wordId", actualWord.getWordId());
			actualWordMap.put("wordName", actualWord.getWordName());
			
			result.add(actualWordMap);
		}
		
		logger.debug("RegisterController - processWordList() - End");
		
		return result;
	}
}
