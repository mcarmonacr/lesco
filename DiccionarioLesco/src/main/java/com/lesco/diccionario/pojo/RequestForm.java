/**
 * 
 */
package com.lesco.diccionario.pojo;

import java.util.Date;

import com.lesco.diccionario.utils.CustomerDateAndTimeDeserialize;

/**
 * RegisterForm POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

public class RequestForm{

	private String wordName;
	private String description;
	/**
	 * @return the wordName
	 */
	public String getWordName() {
		return wordName;
	}
	/**
	 * @param wordName the wordName to set
	 */
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
