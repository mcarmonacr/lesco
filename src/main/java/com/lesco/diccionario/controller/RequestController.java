package com.lesco.diccionario.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.lesco.diccionario.dao.RequestDAO;
import com.lesco.diccionario.dao.UserDAO;
import com.lesco.diccionario.dao.WordDAO;
import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.Request;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.pojo.AjaxResponseBody;
import com.lesco.diccionario.pojo.RequestForm;
import com.lesco.diccionario.utils.LescoConstants;

/**
 * Handles all the request related operations
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
@RequestMapping("/solicitud")
public class RequestController {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RequestController.class);

	@Autowired
	private UserDAO userDAO;
		
	@Autowired
	private RequestDAO requestDAO;
	
	@Autowired
	private WordDAO wordDAO;
	
	/**
	 * Service that registers the user into the site
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/agregarSolicitud", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody agregarSolicitud(@RequestBody RequestForm registerForm, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		
		logger.debug("RequestController - agregarSolicitud() - Start");
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
		try{
			//Saves the user to the database
			String resultadoSalvar= salvarSolicitud(registerForm, httpRequest);
			
			//Response toggle based on the save return
			if(LescoConstants.SUCCESS_MESSAGE.equals(resultadoSalvar)){
				ajaxResponse.setCode(LescoConstants.SUCCESS_CODE);
				ajaxResponse.setMessage(LescoConstants.SUCCESS_MESSAGE);
			}else{
				ajaxResponse.setCode(LescoConstants.ERROR_CODE);
				ajaxResponse.setMessage(LescoConstants.ERROR_MESSAGE);
			}
		}catch(Exception e){
			logger.error("RequestController - agregarSolicitud() - Error", e);
		}

		logger.debug("RequestController - agregarSolicitud() - End");
		
		return ajaxResponse;
	}
		
	/**
	 * Get the request corresponding to the sent ID
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: requestId
	 */
	@RequestMapping(value= "/obtenerSolicitud", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE/*, produces=MediaType.APPLICATION_JSON_VALUE*/)
	public @ResponseBody AjaxResponseBody obtenerSolicitud(@RequestBody Map<String, String> json, HttpServletResponse httpResponse){
		
		AjaxResponseBody ajaxResponse = new AjaxResponseBody();
		
//		httpResponse.setCharacterEncoding("ISO-8859-1");
//		httpResponse.setHeader("Content-Type", "application/json; charset=ISO-8859-1");
		
		logger.debug("RequestController - obtenerSolicitud() - Start");
		
		try{
			///Checks if the word id comes from the request
			if (json.get("requestId") != null){			
				Integer requestId = Integer.parseInt(json.get("requestId"));
				
				if(requestId != null){
					Request request= requestDAO.findById(requestId);
					
					//If the word is found in the database
					if(request != null){
						Map <String, Object> requestMap = new HashMap <String, Object> ();
						requestMap.put("requestId", requestId.toString());
						requestMap.put("wordName", request.getWordName());						
						requestMap.put("description", request.getDescription());
						
						ajaxResponse.setContent(requestMap);
						ajaxResponse.setCode(LescoConstants.SUCCESS_CODE);
						ajaxResponse.setMessage(LescoConstants.SUCCESS_MESSAGE);
					}else{
						ajaxResponse.setCode(LescoConstants.ERROR_CODE);
						ajaxResponse.setMessage(LescoConstants.ERROR_MESSAGE);
					}
				}
			} else{
				ajaxResponse.setCode(LescoConstants.ERROR_CODE);
				ajaxResponse.setMessage(LescoConstants.ERROR_MESSAGE);
			}
		}catch(Exception e){
			logger.error("RequestController - obtenerSolicitud() - Error", e);
		}

		logger.debug("RequestController - obtenerSolicitud() - End");
		
		return ajaxResponse;
	}
	
	/**
	 * Gets all terms form the DB that match the search input pattern
	 * 
	 * Type: Json POST method
	 * 
	 * @param registerForm. Contains fields: userName, emailAddress, password, passwordConfirmation, private String birthdate ,termsAndConditions.
	 */
	@RequestMapping(value= "/obtenerListaSolicitudes", method = RequestMethod.POST, headers = "Accept=application/json", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody obtenerListaSolicitudes(@RequestBody Map<String, String> json){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		logger.debug("RequestController - obtenerListaSolicitudes() - Start");
		
		try{
			List<Request> requestList = new ArrayList<Request>();
			
			//Validate input
			if (json.get("requestInput") != null && !json.get("requestInput").isEmpty()){

				requestList = requestDAO.findByPattern(json.get("requestInput"));
			} else {
				//If there wasn't any input, get them all
				if(requestList.size() == 0){
					requestList = requestDAO.list();
				}
			}
			
			Map <String, Object> requestsMap = new HashMap <String, Object> ();
			
			//Get only the list of words and its IDs 
			requestsMap.put("requestsList", processRequestList(requestList));
			result.setContent(requestsMap);
					
			//Checks if the input user name already exists in the database
			if(requestList != null && !requestList.isEmpty()){			
				result.setMessage("Sucess");
				result.setCode("000");
			}else{
				result.setMessage("List is empty");
				result.setCode("001");
			}
		}catch(Exception e){
			logger.error("RequestController - obtenerListaSolicitudes() - Error", e);
		}
		
		logger.debug("RequestController - obtenerListaSolicitudes() - End");
		
		return result;
	}
	
	
	/**** Private Methods ****/
	
	/**
	 * Stores the new request into the database
	 * 
	 * @param requestForm. Contains fields: wordName, description
	 */
	private String salvarSolicitud(RequestForm requestForm, HttpServletRequest httpRequest){
		
		logger.debug("RequestController - salvarSolicitud() - Start");
		
		//String to the return, with the operation result
		String isUserSaved= "";
		
		try{
			//Validates that all values that come from the form
			if(requestForm.getWordName() != null ){
						
				//Checks if the word already exists in the word and request table
				if(requestDAO.checkWordName(requestForm.getWordName().trim()).equals(false) && wordDAO.checkWordName(requestForm.getWordName().trim()).equals(false)){

					//Get user session
					HttpSession session = httpRequest.getSession();
					
					//Get the current logged in user emailAddress
					String userEmail = session.getAttribute("userEmail").toString();
					
					//Obtain the User that belongs to the email
					ProfileDetail profileDetailQuery = userDAO.findByEmailAddress(userEmail);
					ProfileDetail ProfileDetailReference = userDAO.findById(profileDetailQuery.getProfileDetailId());
					UserProfile userProfile = ProfileDetailReference.getUserProfile();
					
					//New Request
					Request request = new Request();
					request.setDescription(requestForm.getDescription());
					request.setWordName(requestForm.getWordName());
					request.setUserProfile(userProfile);
					
					//This saves both, the User Profile and the Request instances into the DB
					requestDAO.save(request);
					
					//If I wanted to get the ID, I'd have to do something like:
					//request.getId();
				}
				isUserSaved= LescoConstants.SUCCESS_MESSAGE;
			}else{
				isUserSaved= LescoConstants.ERROR_MESSAGE;
			}
		}catch(Exception e){
			logger.error("RequestController - salvarSolicitud() - Error", e);
		}

		logger.debug("RequestController - salvarSolicitud() - Start");
		
		return isUserSaved;
	}
	
	/**
	 * Get the raw data and place the necessary data in the resulting map
	 * 
	 * @param requestList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List processRequestList(List<Request> requestList){
		
		logger.debug("RequestController - processRequestList() - Start");
		
		List result = new ArrayList();
		
		try{
			//Processes the word's list and add the necessary data in the resulting map
			for(Request actualRequest:requestList){
				Map actualRequestMap = new HashMap();
				
				actualRequestMap.put("requestId", actualRequest.getRequestId());
				actualRequestMap.put("wordName", actualRequest.getWordName());
				
				result.add(actualRequestMap);
			}
		}catch(Exception e){
			logger.error("RequestController - processRequestList() - Error", e);
		}
		
		logger.debug("RequestController - processRequestList() - End");
		
		return result;
	}
}