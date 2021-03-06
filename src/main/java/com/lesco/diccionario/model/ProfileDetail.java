package com.lesco.diccionario.model;

import java.util.Date;

/**
 * ProfileDetail POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class ProfileDetail {

	private Integer profileDetailId;
	private String email;
	private Date birthDate;
	private Boolean termsAndConditions;
	private UserProfile userProfile;


	
	/**
	 * @return the profileDetailId
	 */
	public Integer getProfileDetailId() {
		return profileDetailId;
	}
	/**
	 * @param profileDetailId the profileDetailId to set
	 */
	public void setProfileDetailId(Integer profileDetailId) {
		this.profileDetailId = profileDetailId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/**
	 * @return the userProfile
	 */
	public UserProfile getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
		
}
