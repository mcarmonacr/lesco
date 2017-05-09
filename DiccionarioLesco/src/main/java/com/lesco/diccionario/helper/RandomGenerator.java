/**
 * 
 */
package com.lesco.diccionario.helper;

import java.security.SecureRandom;

/**
 * @author Mario Alonso Carmona Dinarte
 *
 */
public class RandomGenerator {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	
	//Taken from: http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
}
