package com.lesco.diccionario.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
import com.lesco.diccionario.model.Request;
import com.lesco.diccionario.model.Video;
import com.lesco.diccionario.model.Word;

/**
 * Main Controller. Contains the Home page
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
public class LescoController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(LescoController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private WordDAO wordDAO;
	
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private YoutubeHelper youtubeHelper;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PreferredWordDAO preferredWordDAO;
	
	/**
	 * DiccioanrioLesco Home Page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView diccionarioLesco(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		
		logger.debug("LescoController - diccionarioLesco() - Start");
 
		ModelAndView mv = new ModelAndView("home");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		//Get all the categories
		List<Category> listCategories = categoryDAO.list();
		
		List<Word> listMyWords = null;

		//Get user session
		HttpSession session = httpRequest.getSession();
		
		if(session != null && session.getAttribute("userEmail") != null) {
			//Get the current logged in user emailAddress
			String userEmail = session.getAttribute("userEmail").toString();
			
			//Obtain the User that belongs to the email
			ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
			
			listMyWords = getWordsFromList(preferredWordDAO.findByUser(profileDetailQuery.getProfileDetailId()));
		}
		
		//Get all the categories
		List<Word> listWords = wordDAO.list();
		
		//mv.addObject("youtubeVideo", youtubeHelper.getVideoMetadata());
		
		//Get random video
		//TODO Include all the missing details of the video
		
		Word randomWord = getRandomWord(listWords);
		
		mv.addObject("randomWord", randomWord);
		mv.addObject("videosMetadata", getVideosMetadata(randomWord));
		mv.addObject("listCategories", listCategories);
		mv.addObject("listWords", listWords);
		mv.addObject("listMyWords", listMyWords);
		
		logger.debug("LescoController - diccionarioLesco() - End");
		
		return mv;
	}
	
	/**
	 * Registry page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/registrarse")
	public ModelAndView registrarse() {
		
		logger.debug("LescoController - registrarse() - Start");
 
		ModelAndView mv = new ModelAndView("register");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - registrarse() - End");
		
		return mv;
	}
	
	/**
	 * About page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/acerca")
	public ModelAndView acerca() {
		
		logger.debug("LescoController - acerca() - Start");
 
		ModelAndView mv = new ModelAndView("about");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - acerca() - End");
		
		return mv;
	}
	
	/**
	 * Add term page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/agregar")
	public ModelAndView agregar() {
		
		logger.debug("LescoController - agregar() - Start");
 
		ModelAndView mv = new ModelAndView("addTerm");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		List<Request> requestList = requestDAO.list();
		mv.addObject("requestList", requestList);
		
		List<Category> categoryList = categoryDAO.list();
		mv.addObject("listCategories", categoryList);
		
		logger.debug("LescoController - agregar() - End");
		
		return mv;
	}
	
	
	/**
	 * Contact page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/contacto")
	public ModelAndView contacto() {
		
		logger.debug("LescoController - contacto() - Start");
 
		ModelAndView mv = new ModelAndView("contact");
		//mv.addObject("message", message);
		//mv.addObject("name", name);
		
		logger.debug("LescoController - contacto() - End");
		
		return mv;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/ingresar"})
	public ModelAndView ingresar() {
		
		logger.debug("LescoController - ingresar() - Start");
		 
		ModelAndView mv = new ModelAndView("login");
		
		logger.debug("LescoController - ingresar() - End");
		
		return mv;
	}
	
	
	/*** Private methods ***/
	
	/**
	 * Obtains a random word from the database
	 * 
	 * @param listWords
	 * @return Word Object
	 */
	private Word getRandomWord(List<Word> listWords){
		
		logger.debug("LescoController - getRandomWord() - Start");
		
		//New random word. This is the one been returned
		Word randomWord = new Word();
		
		try{
			//If the current list of words is not empty
			if(listWords.size() > 0) {
				int START = 0; //Start index is 0
			    int END = listWords.size() - 1; //The maximum index should be one number less than the maximum because indexes start at 0
			    
			    //Create a random seed and the gets a random number based on the given parameters
			    Random random = new Random();
				Integer randomNumber = getRandomInteger(START, END, random);
				
				//Get random word
				randomWord = listWords.get(randomNumber);
			} else {	
				//In case there aren't any videos available create empty objects 
				
				Video video = new Video();
				video.setDefinitionYoutubeVideoID("");
				video.setExampleYoutubeVideoID("");
				video.setExplanationYoutubeVideoID("");
				video.setTermYoutubeVideoID("");
								
				randomWord.setWordName("");
				randomWord.setDefinition("");
				randomWord.setExample("");
				randomWord.setExplanation("");
				randomWord.setNumberOfVisits(0);
				randomWord.setVideo(video);
			}
		} catch (Exception e){
			logger.error("LescoController - getRandomWord() - Error: ", e);
		}
		
		logger.debug("LescoController - getRandomWord() - Start");
		
		//Returns a random word
		return randomWord;
	}
	
	/**
	 * Returns a random integer based on the given parameters
	 * 
	 * @param aStart - Start index
	 * @param aEnd - End Index
	 * @param aRandom - Random Java generator 
	 * @return random integer
	 */
	private static int getRandomInteger(int aStart, int aEnd, Random aRandom){
		
		logger.debug("LescoController - getRandomInteger() - Start");
		
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    //log("Generated : " + randomNumber);
	    
	    logger.debug("LescoController - getRandomInteger() - End");
	    
	    return randomNumber;
	  }	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getVideosMetadata(Word randomWord) { 
		Map result= new HashMap();
		
//		com.google.api.services.youtube.model.Video definitionVideo= youtubeHelper.getVideoMetadata(randomWord.getVideo().getDefinitionYoutubeVideoID());
//		com.google.api.services.youtube.model.Video exampleVideo= youtubeHelper.getVideoMetadata(randomWord.getVideo().getExampleYoutubeVideoID());
//		com.google.api.services.youtube.model.Video explanationVideo= youtubeHelper.getVideoMetadata(randomWord.getVideo().getExplanationYoutubeVideoID());
//		com.google.api.services.youtube.model.Video termVideo= youtubeHelper.getVideoMetadata(randomWord.getVideo().getTermYoutubeVideoID());
		
		
		//Setup default result values
		result.put("definitionVideo", 0);
		result.put("definitionVideoLikes", 0);
		result.put("definitionVideoDislikes", 0);
		result.put("definitionVideoRating", "none");
		
		result.put("exampleVideo", 0);
		result.put("exampleVideoLikes", 0);
		result.put("exampleVideoDislikes", 0);
		result.put("exampleVideoRating", "none");
		
		result.put("explanationVideo", 0);
		result.put("explanationVideoLikes", 0);
		result.put("explanationVideoDislikes", 0);
		result.put("explanationVideoRating", "none");
		
		result.put("termVideo", 0);
		result.put("termVideoLikes", 0);
		result.put("termVideoDislikes", 0);
		result.put("termVideoRating", "none");
		
		if(randomWord == null || randomWord.getWordId() == null) {			
			return result;	
		}
		
		com.google.api.services.youtube.model.Video definitionVideo= youtubeHelper.getVideoMetadata("4z7rnfxhdms");
		com.google.api.services.youtube.model.Video exampleVideo= youtubeHelper.getVideoMetadata("63xrbVSXbXA");
		com.google.api.services.youtube.model.Video explanationVideo= youtubeHelper.getVideoMetadata("K64bcDY-Oko");
		com.google.api.services.youtube.model.Video termVideo= youtubeHelper.getVideoMetadata("PX4IBJNuMsE");
		
		VideoRating definitionVideoRating= youtubeHelper.getVideoRating("4z7rnfxhdms");
		VideoRating exampleVideoRating= youtubeHelper.getVideoRating("63xrbVSXbXA");
		VideoRating explanationVideoRating= youtubeHelper.getVideoRating("K64bcDY-Oko");
		VideoRating termVideoRating= youtubeHelper.getVideoRating("PX4IBJNuMsE");
		
		//If the values exist, then the ones in the map get overridden
		if (definitionVideo != null && definitionVideoRating != null) {
			result.put("definitionVideo", definitionVideo.getStatistics().getViewCount());
			result.put("definitionVideoLikes", definitionVideo.getStatistics().getLikeCount());
			result.put("definitionVideoDislikes", definitionVideo.getStatistics().getDislikeCount());
			result.put("definitionVideoRating", definitionVideoRating.getRating());
		} 
		
		if (exampleVideo != null) {
			result.put("exampleVideo", exampleVideo.getStatistics().getViewCount());
			result.put("exampleVideoLikes", exampleVideo.getStatistics().getLikeCount());
			result.put("exampleVideoDislikes", exampleVideo.getStatistics().getDislikeCount());
			result.put("exampleVideoRating", exampleVideoRating.getRating());
		} 
		
		if (explanationVideo != null) {
			result.put("explanationVideo", explanationVideo.getStatistics().getViewCount());
			result.put("explanationVideoLikes", explanationVideo.getStatistics().getLikeCount());
			result.put("explanationVideoDislikes", explanationVideo.getStatistics().getDislikeCount());
			result.put("explanationVideoRating", explanationVideoRating.getRating());
		} 
		
		if (termVideo != null) {
			result.put("termVideo", termVideo.getStatistics().getViewCount());
			result.put("termVideoLikes", termVideo.getStatistics().getLikeCount());
			result.put("termVideoDislikes", termVideo.getStatistics().getDislikeCount());
			result.put("termVideoRating", termVideoRating.getRating());
		} 

		return result;	
	}
	
	private List<Word> getWordsFromList(List<PreferredWord> preferredWordList){
		
		List<Word> result = new ArrayList<Word>();
		
		//Processes the word's list and add the necessary data in the resulting map
		for(PreferredWord actualPreferredWord:preferredWordList){
			
			Word actualWord= new Word();
			
			actualWord= wordDAO.findById(actualPreferredWord.getUserProfileId());
			
			result.add(actualWord);
		}
		
		return result;
		
	}
}
