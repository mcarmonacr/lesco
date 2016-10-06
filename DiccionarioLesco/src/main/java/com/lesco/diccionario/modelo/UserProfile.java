package com.lesco.diccionario.modelo;

public class UserProfile {

	private Integer id;
	private String email;
	private Boolean termsnAndConditions;
	private User user;
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
	 * @return the termsnAndConditions
	 */
	public Boolean getTermsnAndConditions() {
		return termsnAndConditions;
	}
	/**
	 * @param termsnAndConditions the termsnAndConditions to set
	 */
	public void setTermsnAndConditions(Boolean termsnAndConditions) {
		this.termsnAndConditions = termsnAndConditions;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
		
}
