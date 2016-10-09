package com.lesco.diccionario.modelo;

public class ProfileDetail {

	private Integer id;
	private String email;
	private Boolean termsAndConditions;
	private UserProfile userProfile;
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