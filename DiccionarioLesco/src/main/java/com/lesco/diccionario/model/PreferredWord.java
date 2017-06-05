package com.lesco.diccionario.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Word POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class PreferredWord {

	private Integer preferredWordId;
	private Integer wordId;
	private Integer userProfileId;
	/**
	 * @return the preferredWordId
	 */
	public Integer getPreferredWordId() {
		return preferredWordId;
	}
	/**
	 * @param preferredWordId the preferredWordId to set
	 */
	public void setPreferredWordId(Integer preferredWordId) {
		this.preferredWordId = preferredWordId;
	}
	/**
	 * @return the wordId
	 */
	public Integer getWordId() {
		return wordId;
	}
	/**
	 * @param wordId the wordId to set
	 */
	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}
	/**
	 * @return the userProfileId
	 */
	public Integer getUserProfileId() {
		return userProfileId;
	}
	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}
}
