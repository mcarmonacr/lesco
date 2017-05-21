/**
 * 
 */
package com.lesco.diccionario.helper;

import java.security.SecureRandom;

import org.apache.log4j.Logger;

/**
 * Class to generate random strings
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 * Taken from: http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 */
public class RandomGenerator {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RandomGenerator.class);
	
	//Available symbols
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
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
	
	for( int i = 0; i < stringLenght; i++ ) 
		sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   
	logger.debug("RandomGenerator - randomString() - End");
	   
	return sb.toString();
	}
}
