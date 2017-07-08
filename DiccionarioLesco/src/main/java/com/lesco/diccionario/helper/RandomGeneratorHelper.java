/**
 * 
 */
package com.lesco.diccionario.helper;

import java.security.SecureRandom;

import org.apache.log4j.Logger;

import com.lesco.diccionario.utils.LescoConstants;

/**
 * Class to generate random strings
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 * Taken from: http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 */
public class RandomGeneratorHelper {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RandomGeneratorHelper.class);
	
	//Secure random static instance
	static SecureRandom rnd = new SecureRandom();

	/**
	 * Generates a random string of the given length
	 * 
	 * @param stringLenght
	 * @return
	 */
	public String randomString( int stringLenght ){
		
	logger.debug("RandomGenerator - randomString() - Start");
	
	StringBuilder sb = new StringBuilder( stringLenght );
	
	try{
		for( int i = 0; i < stringLenght; i++ ) 
			sb.append( LescoConstants.AVAILABLE_SYMBOLS.charAt( rnd.nextInt(LescoConstants.AVAILABLE_SYMBOLS.length()) ) );
	}catch(Exception e){
		logger.error("RandomGenerator - randomString() - Error", e);
	}

	logger.debug("RandomGenerator - randomString() - End");
	   
	return sb.toString();
	}
}
