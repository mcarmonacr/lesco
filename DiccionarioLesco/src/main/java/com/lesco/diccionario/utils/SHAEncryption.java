/**
 * 
 */
package com.lesco.diccionario.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

/**
 * Encryption utility class
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class SHAEncryption {
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(SHAEncryption.class);
	
	/**
	 * Get Hashed password
	 * 
	 * @param passwordToHash
	 * @param salt
	 * @return
	 */
	public String getHashedPassword(String passwordToHash, byte[] salt) {
		
		logger.debug("SHAEncryption - getHashedPassword() - Start");
         
        String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
        
        logger.debug("SHAEncryption - getHashedPassword() - End");
        
        return securePassword;
    }
 
	/**
	 * Get SHA 512 secure string
	 * 
	 * @param passwordToHash
	 * @param salt
	 * @return
	 */
    private static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt) {
    	
    	logger.debug("SHAEncryption - get_SHA_512_SecurePassword() - Start");
    	
    	//Variable to be returned
        String generatedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            logger.error("SHAEncryption - get_SHA_512_SecurePassword() - Error: ", e);
        }
        
        logger.debug("SHAEncryption - get_SHA_512_SecurePassword() - End");
        
        return generatedPassword;
    }

    /**
     * Get random Salt value
     * 
     * @return
     */
    public static byte[] getSalt()
    {
    	logger.debug("SHAEncryption - getSalt() - Start");
    	
        SecureRandom sr;
        byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			logger.error("SHAEncryption - getSalt() - Error", e);
		}
        
		logger.debug("SHAEncryption - getSalt() - End");
		
        return salt;
    }
}
