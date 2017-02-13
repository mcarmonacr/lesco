/**
 * 
 */
package com.lesco.diccionario.pojo;

import java.util.Map;

/**
 * 
 * Generic Response Body for all Ajax request
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class AjaxResponseBody{
	
	private String code;
	private String message;
	private Map <String, Object> content;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the content
	 */
	public Map<String, Object> getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

}
