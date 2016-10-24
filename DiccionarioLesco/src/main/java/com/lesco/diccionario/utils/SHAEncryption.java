/**
 * 
 */
package com.lesco.diccionario.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Mario Alonso Carmona Dinarte - 2016
 * @email monacar89@hotmail.com
 *
 */
public class SHAEncryption {
	
	public String getHashedPassword(String passwordToHash, byte[] salt) {
        //byte[] salt = getSalt();
         
        String securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
        
        return securePassword;
    }
 
    private static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * Get random Salt value
     * 
     * @return
     */
    public static byte[] getSalt()
    {
        SecureRandom sr;
        byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return salt;
    }
}
