package com.lesco.diccionario.model;

import java.util.HashSet;
import java.util.Set;

/**
 * UserProfile Pojo
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class UserProfile {

	private Integer userProfileId;
	private String userName;
	private String userPassword;
	private byte[] salt;
	private String userRole;
	
	//Foreign Key's
	private ProfileDetail profileDetail;
	private Set<Word> words = new HashSet<Word>(0);

	
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
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the salt
	 */
	public byte[] getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	/**
	 * @return the profileDetail
	 */
	public ProfileDetail getProfileDetail() {
		return profileDetail;
	}
	/**
	 * @param profileDetail the profileDetail to set
	 */
	public void setProfileDetail(ProfileDetail profileDetail) {
		this.profileDetail = profileDetail;
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
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
