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

public class RegisterForm{

	private String userName;
	private String emailAddress;
	private String password;
	private String passwordConfirmation;
	//private Boolean administrator;
	
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date birthDate;
	
	private Boolean termsAndConditions;
	
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
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the termsAndConditions
	 */
	public Boolean getTermsAndConditions() {
		return termsAndConditions;
	}
	/**
	 * @param termsAndConditions the termsAndConditions to set
	 */
	public void setTermsAndConditions(Boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
//	/**
//	 * @return the administrator
//	 */
//	public Boolean getAdministrator() {
//		return administrator;
//	}
//	/**
//	 * @param administrator the administrator to set
//	 */
//	public void setAdministrator(Boolean administrator) {
//		this.administrator = administrator;
//	}
	
}
