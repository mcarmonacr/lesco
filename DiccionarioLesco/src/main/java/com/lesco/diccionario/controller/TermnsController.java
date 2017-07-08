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

import com.google.api.services.youtube.model.VideoRating;
import com.lesco.diccionario.dao.CategoryDAO;
import com.lesco.diccionario.dao.PreferredWordDAO;
import com.lesco.diccionario.dao.RequestDAO;
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.dao.WordDAO;
import com.lesco.diccionario.helper.YoutubeHelper;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.PreferredWord;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.model.Video;
import com.lesco.diccionario.model.Word;
import com.lesco.diccionario.pojo.AddTermForm;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RegisterForm;
import com.lesco.diccionario.utils.LescoConstants;

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
	
	@Autowired
	private PreferredWordDAO preferredWordDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
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
			@RequestPart(value = "definitionVideo", required = false) MultipartFile definitionVideoFile, @RequestPart(value = "explanationVideo", required = false) MultipartFile explanationVideoFile, 
			@RequestPart(value = "exampleVideo", required = false) MultipartFile exampleVideoFile,
			HttpServletRequest request, HttpServletResponse response){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		try{
			logger.debug("TermnsController - agregarTermino() - Start");

			ajaxResponse.setCode(LescoConstants.SUCCESS_CODE);
			ajaxResponse.setMessage(LescoConstants.SUCCESS_MESSAGE);
			
			String resultadoSalvar= "";

			//Validate if the terms already exists in the system
			if(!checkTermExistence(addTermForm)) {
									
				//Saves the user to the database
				resultadoSalvar= salvarTermino(addTermForm, videoFile, definitionVideoFile, explanationVideoFile, exampleVideoFile, request);
	
				//resultadoSalvar= "success";
			}
			//Response toggle based on the save return
			if(LescoConstants.SUCCESS_MESSAGE.equals(resultadoSalvar)){
				
				//Validate if the term is in the requests table, in which case should be deleted from there
				checkRequestExistence(addTermForm);
				
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
		
		logger.debug("TermnsController - obtenerTermino() - Start");
		
		try{
			//Checks if the word id comes from the request
			if (json.get("wordId") != null){			
				Integer wordId = Integer.parseInt(json.get("wordId"));
				
				if(wordId != null){
					Word word= wordDAO.findById(wordId);
					
					//If the word is found in the database
					if(word != null){
						
						Map <String, Object> wordMap = new HashMap <String, Object> ();
						
						wordMap.put("wordId", wordId.toString());
						wordMap.put("wordName", word.getWordName());
						wordMap.put("termYoutubeVideoID", word.getVideo().getTermYoutubeVideoID());
						wordMap.put("termVideoRating", youtubeHelper.getVideoRating(word.getVideo().getTermYoutubeVideoID()));
						wordMap.put("termVideoMetadata", youtubeHelper.getVideoMetadata(word.getVideo().getTermYoutubeVideoID()));					
						
						wordMap.put("definition", word.getDefinition());
						wordMap.put("definitionYoutubeVideoID", word.getVideo().getDefinitionYoutubeVideoID());
						wordMap.put("definitionVideoRating", youtubeHelper.getVideoRating(word.getVideo().getDefinitionYoutubeVideoID()));
						wordMap.put("definitionVideoMetadata", youtubeHelper.getVideoMetadata(word.getVideo().getDefinitionYoutubeVideoID()));
						
						wordMap.put("explanation", word.getExplanation());
						wordMap.put("explanationYoutubeVideoID", word.getVideo().getExplanationYoutubeVideoID());
						wordMap.put("explanationVideoRating", youtubeHelper.getVideoRating(word.getVideo().getExplanationYoutubeVideoID()));
						wordMap.put("explanationVideoMetadata", youtubeHelper.getVideoMetadata(word.getVideo().getExplanationYoutubeVideoID()));
						
						wordMap.put("example", word.getExample());
						wordMap.put("exampleYoutubeVideoID", word.getVideo().getExampleYoutubeVideoID());
						wordMap.put("exampleVideoRating", youtubeHelper.getVideoRating(word.getVideo().getExampleYoutubeVideoID()));
						wordMap.put("exampleVideoMetadata", youtubeHelper.getVideoMetadata(word.getVideo().getExampleYoutubeVideoID()));
						
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
		}catch(Exception e){
			logger.debug("TermnsController - obtenerTermino() - Error", e);
		}

		logger.debug("TermnsController - obtenerTermino() - End");
		
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
	public @ResponseBody AjaxResponseBody obtenerListaTerminos(@RequestBody Map<String, String> json, HttpServletRequest request, HttpServletResponse response){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("TermnsController - obtenerListaTerminos() - Start");
		
		try{
			List<Word> wordsList = new ArrayList<Word>();
			List<Word> listMyWords = new ArrayList<Word>();
			
			//Validate input
			if (json.get("termsInput") != null && !json.get("termsInput").isEmpty()){

				//If there's a category then it's included in the search 
				if(json.get("categoryIdDiv") != null && !json.get("categoryIdDiv").isEmpty()){
					wordsList = wordDAO.findByPatternAndCategoryId(json.get("termsInput"), Integer.parseInt(json.get("categoryIdDiv"))); //wordDAO.list();
				} else {
					//Search the word regardless the category
					wordsList = wordDAO.findByPattern(json.get("termsInput"));
				}
			}
			else {
				//If there wasn't any input, get them all
				if(wordsList.size() == 0){
					wordsList = wordDAO.list();
				}
			}
			
			Map <String, Object> wordsMap = new HashMap <String, Object> ();
			
			//Get user session and sets an attribute in the result datamap
			HttpSession session = request.getSession();
			if(session != null && session.getAttribute("userEmail") != null) {
				
				//Get the current logged in user emailAddress
				String userEmail = session.getAttribute("userEmail").toString();
				
				//Obtain the User that belongs to the email
				ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
				
				listMyWords = getWordsFromList(preferredWordDAO.findByUser(profileDetailQuery.getProfileDetailId()));
				
				wordsMap.put("isSessionValid", true);
			} else {
				wordsMap.put("isSessionValid", false);
			}
			
			// TODO process wordsMap in order to get only the list of words and its IDs 
		
			wordsMap.put("myWordsList", processWordList(listMyWords));
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
		}catch(Exception e){
			logger.debug("TermnsController - obtenerListaTerminos() - Error", e);
		}

		logger.debug("TermnsController - obtenerListaTerminos() - End");
		
		return result;
	}
	
	
	/**
	 * Gets all terms form the DB that match the search input pattern
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: myTermsInput, myCategoryIdDiv
	 */
	@RequestMapping(value= "/obtenerListaMisTerminos", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody obtenerListaMisTerminos(@RequestBody Map<String, String> json, HttpServletRequest request, HttpServletResponse response){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("TermnsController - obtenerListaMisTerminos() - Start");
		
		try{
			List<Word> myWordsList = new ArrayList<Word>();
			
			//Get user session
			HttpSession session = request.getSession();
			
			//Get the current logged in user emailAddress
			String userEmail = session.getAttribute("userEmail").toString();
			
			//Obtain the User that belongs to the email
			ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
			
			if(profileDetailQuery != null){
				//Validate input
				if (json.get("myTermsInput") != null && !json.get("myTermsInput").isEmpty()){

					//If there's a category then it's included in the search 
					if(json.get("myCategoryIdDiv") != null && !json.get("myCategoryIdDiv").isEmpty()){
						myWordsList = preferredWordDAO.findByPatternAndCategoryId(json.get("myTermsInput"), Integer.parseInt(json.get("myCategoryIdDiv")), profileDetailQuery.getProfileDetailId()); //wordDAO.list();
					} else {
						//Search the word regardless the category
						myWordsList = preferredWordDAO.findByPattern(json.get("myTermsInput"), profileDetailQuery.getProfileDetailId());
					}
				} else {
					// TODO
					//If there wasn't any input, get them all
					if(myWordsList.size() == 0){
						myWordsList = preferredWordDAO.listMyWords(profileDetailQuery.getProfileDetailId());
					}
				}
			}

			Map <String, Object> wordsMap = new HashMap <String, Object> ();
						
			// TODO process wordsMap in order to get only the list of words and its IDs 
			wordsMap.put("myWordsList", processWordList(myWordsList));
			result.setContent(wordsMap);
					
			//Checks if the input user name already exists in the database
			if(myWordsList != null && !myWordsList.isEmpty()){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("List is empty");
				result.setCode("001");
			}
		}catch(Exception e){
			logger.debug("TermnsController - obtenerListaMisTerminos() - Error", e);
		}

		logger.debug("TermnsController - obtenerListaMisTerminos() - End");
		
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
		
		logger.debug("TermnsController - verificarUsuario() - Start");
		
		try{
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
		}catch(Exception e){
			logger.debug("TermnsController - verificarUsuario() - Error", e);
		}
		
		logger.debug("TermnsController - verificarUsuario() - End");
		
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
		
		logger.debug("TermnsController - verificarCorreo() - Start");
		
		try{
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
		}catch(Exception e){
			logger.debug("TermnsController - verificarCorreo() - Error", e);
		}

		logger.debug("TermnsController - verificarCorreo() - End");
		
		return result;
	}
	
	/**
	 * Set a word as preferred or not, depending upon it previous status
	 * 
	 * Type: Json POST method
	 * 
	 * @param. Contains fields: wordId, isOneOfMyFavoriteTerms
	 */
	@RequestMapping(value= "/agregarPreferido", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarPreferido(@RequestBody Map<String, String> json, HttpServletRequest request, HttpServletResponse response){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("TermnsController - agregarPreferido() - Start");
		
		try{
			Map <String, Object> resultMap = new HashMap <String, Object> ();
			resultMap.put("isOneOfMyFavoriteTerms", "");
			
			///Checks if the word id comes from the request
			if (json.get("wordId") != null){			
				Integer wordId = Integer.parseInt(json.get("wordId"));
				if(wordId != null){
					Word word= wordDAO.findById(wordId);
					
					//If the word is found in the database
					if(word != null){
						//Get user session
						HttpSession session = request.getSession();
						
						//Get the current logged in user emailAddress
						String userEmail = session.getAttribute("userEmail").toString();
						
						//Obtain the User that belongs to the email
						ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
						
						if(profileDetailQuery != null){

							List<PreferredWord> preferredWordsList = preferredWordDAO.findByWord(wordId);
							
							//If the condition is met means that the term is already a favorite and should be removed from there
							if(preferredWordsList != null && !preferredWordsList.isEmpty()) {
								
								//Get the entity that needs to be deleted
								PreferredWord preferredWord = preferredWordDAO.findByWordAndUser(wordId, profileDetailQuery.getProfileDetailId());
								
								PreferredWord preferredWordReference = preferredWordDAO.findById(preferredWord.getPreferredWordId());
								
								//Delete the entity
								preferredWordDAO.delete(preferredWordReference);
								resultMap.put("isOneOfMyFavoriteTerms", false);							
							} else {
								PreferredWord preferredWord = new PreferredWord();
								
								preferredWord.setWordId(wordId);
								preferredWord.setUserProfileId(profileDetailQuery.getProfileDetailId());
								
								preferredWordDAO.save(preferredWord);
								resultMap.put("isOneOfMyFavoriteTerms", true);
							}
							
							//Gets the most recent list of preferred words
							List<Word> listMyWords = getWordsFromList(preferredWordDAO.findByUser(profileDetailQuery.getProfileDetailId()));
							resultMap.put("listMyWords", processWordList(listMyWords));
						}
						result.setContent(resultMap);
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
		}catch(Exception e){
			logger.debug("TermnsController - agregarPreferido() - Error", e);
		}
		
		logger.debug("TermnsController - agregarPreferido() - End");
		
		return result;
	}
	
	/**
	 * Give a rate to a particular video
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: myTermsInput, myCategoryIdDiv
	 */
	@RequestMapping(value= "/evaluarVideo", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody evaluarVideo(@RequestBody Map<String, String> json){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("TermnsController - evaluarVideo() - Start");
		
		try{
			//Validate input
			if (json.get("videoId") != null && json.get("action") != null){
				
				//first cleans the video from any previous rate
				youtubeHelper.likeAVideo(json.get("videoId"), "none");
				
				//Set the new rating
				youtubeHelper.likeAVideo(json.get("videoId"), json.get("action"));
				
				//Get the video rating and metadata
				VideoRating videoRating= youtubeHelper.getVideoRating(json.get("videoId"));
				com.google.api.services.youtube.model.Video videoMetadata= youtubeHelper.getVideoMetadata(json.get("videoId"));
				
				//Map resultMap = new HashMap();
				Map <String, Object> resultMap = new HashMap <String, Object> ();
				
				//Set the video rating and metadata
				resultMap.put("videoRating", videoRating);
				resultMap.put("videoMetadata", videoMetadata);
				
				result.setContent(resultMap);
				result.setMessage("Sucess");
				result.setCode("000");
			}
		} catch (Exception e){
			logger.debug("TermnsController - evaluarVideo() - Error", e);
			
			result.setMessage("There was an error");
			result.setCode("001");
		}
		
		logger.debug("TermnsController - evaluarVideo() - End");
		
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
		
		logger.debug("TermnsController - salvarTermino() - Start");
		
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
				//String termYoutubeVideoID = youtubeHelper.uploadVideo(wordName+ "en Lenguaje de Se�as Costarricense (LESCO)" , wordName+ "en Lenguaje de Se�as Costarricense (LESCO)" , videoFile);
				
				String termYoutubeVideoID = "X7PpGPOHVrA";
				
				//Rest of the videos IDs
				String definitionYoutubeVideoID = "";
				String explanationYoutubeVideoID = "";
				String exampleYoutubeVideoID = "";
				
				if (definition != null && definitionVideoFile != null){
					//definitionYoutubeVideoID = youtubeHelper.uploadVideo("Definici�n en LESCO del t�rmino " + wordName, "Definici�n en LESCO del t�rmino: " + wordName + " - " + definition, definitionVideoFile);
				}
				
				if (explanation != null && explanationVideoFile != null){
					//explanationYoutubeVideoID = youtubeHelper.uploadVideo("Explicaci�n en LESCO del t�rmino " + wordName, "Explicaci�n en LESCO del t�rmino: " + wordName + " - " + explanation, explanationVideoFile);
				}
				
				if (example != null && exampleVideoFile != null){
					//exampleYoutubeVideoID = youtubeHelper.uploadVideo("Ejemplo en LESCO del t�rmino " + wordName, "Ejemplo en LESCO del t�rmino: " + wordName + " - " + example, exampleVideoFile);
				}
				
				
				termYoutubeVideoID= "";
				
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
					result= LescoConstants.FAILURE_MESSAGE;
				}
				result= LescoConstants.SUCCESS_MESSAGE;
			}else{
				result= LescoConstants.FAILURE_MESSAGE;
			}
		} catch(Exception e) {
			logger.debug("TermnsController - salvarTermino() - Error: ", e);
		}

		logger.debug("TermnsController - salvarTermino() - End");
		
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
		
		logger.debug("TermnsController - processWordList() - Start");
		List result = new ArrayList();
		
		try{
			//Processes the word's list and add the necessary data in the resulting map
			for(Word actualWord:wordList){
				Map actualWordMap = new HashMap();
				
				actualWordMap.put("wordId", actualWord.getWordId());
				actualWordMap.put("wordName", actualWord.getWordName());
				
				result.add(actualWordMap);
			}
		}catch(Exception e){
			logger.debug("TermnsController - processWordList() - Error", e);
		}
				
		logger.debug("TermnsController - processWordList() - End");
		
		return result;
	}
	
	/**
	 * Checks if the input term already exists in the database
	 * 
	 * @param addTermForm
	 * @return
	 */
	private Boolean checkTermExistence(AddTermForm addTermForm) {
		
		logger.debug("TermnsController - checkTermExistence() - Start");
		
		Boolean result = false;
		
		try{
			if(wordDAO.checkWordName(addTermForm.getWordName()) == true) {
				result= true;
			}
		}catch(Exception e){
			logger.debug("TermnsController - checkTermExistence() - Error", e);
		}

		logger.debug("TermnsController - checkTermExistence() - End");
		
		return result;
	}
	
	/**
	 * Checks if a input request already exists in the database
	 * 
	 * @param addTermForm
	 * @return
	 */
	private Boolean checkRequestExistence(AddTermForm addTermForm) {
		
		logger.debug("TermnsController - checkRequestExistence() - Start");
		
		Boolean result= false;
		
		try{
			if(requestDAO.checkWordName(addTermForm.getWordName()) == true) {
				result= requestDAO.deleteByWordName(addTermForm.getWordName());
			}
		}catch(Exception e){
			logger.debug("TermnsController - checkRequestExistence() - Error", e);
		}

		logger.debug("TermnsController - checkRequestExistence() - End");
		
		return result;
	}
	
	/**
	 * From the preferred list, based on the word id, the actual word is retrieved from the database
	 * 
	 * @param preferredWordList
	 * @return
	 */
	private List<Word> getWordsFromList(List<PreferredWord> preferredWordList){
	
	logger.debug("TermnsController - getWordsFromList() - Start");
	List<Word> result = new ArrayList<Word>();
	
		try{
			//Processes the word's list and add the necessary data in the resulting map
			for(PreferredWord actualPreferredWord:preferredWordList){
				
				Word actualWord= new Word();
				
				actualWord= wordDAO.findById(actualPreferredWord.getWordId());
				
				result.add(actualWord);
			}
		}catch(Exception e){
			logger.debug("TermnsController - getWordsFromList() - Error", e);
		}
	
		logger.debug("TermnsController - getWordsFromList() - End");
		
		return result;
	}
}
