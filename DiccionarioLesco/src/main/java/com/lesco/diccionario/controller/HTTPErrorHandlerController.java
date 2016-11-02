/**
 * 
 */
package com.lesco.diccionario.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles the website general error pages
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
@Controller
public class HTTPErrorHandlerController {

	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(HTTPErrorHandlerController.class);
	
	//Root path for the FTL error folder
	String filesRootFolderPath = "/error";
	
	@RequestMapping(value="/400")
	public String error400(){
		logger.error("HTTPErrorHandlerController - error400()");
		return filesRootFolderPath+"/400";
	}
	
	@RequestMapping(value="/404")
	public String error404(){
		logger.error("HTTPErrorHandlerController - error404()");
		return filesRootFolderPath+"/404";
	}
	
	@RequestMapping(value="/500")
	public String error500(){
		logger.error("HTTPErrorHandlerController - error500()");
		return filesRootFolderPath+"/500";
	}

}
