package com.lesco.diccionario.model;

/**
 * UserProfile Pojo
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class UserProfile {

	private Integer id;
	private String userName;
	private String userPassword;
	private byte[] salt;
	private ProfileDetail profileDetail;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

}
