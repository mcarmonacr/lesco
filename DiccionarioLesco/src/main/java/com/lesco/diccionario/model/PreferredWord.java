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
	
	//Foreign Key
	private Set<Word> words = new HashSet<Word>(0);
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>(0);

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
	 * @return the words
	 */
	public Set<Word> getWords() {
		return words;
	}
	/**
	 * @param words the words to set
	 */
	public void setWords(Set<Word> words) {
		this.words = words;
	}
	/**
	 * @return the userProfiles
	 */
	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}
	/**
	 * @param userProfiles the userProfiles to set
	 */
	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
}
