package com.lesco.diccionario.dao;

import com.lesco.diccionario.model.ProfileDetail;
import com.lesco.diccionario.model.UserProfile;


/**
 * User Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public interface UserDAO {
	
	/**
	 * Saves a new user
	 * @param userProfile
	 */
	public void save(UserProfile userProfile);

	//public List<Category> list();
	
	/**
	 * Check if the given user name already exists
	 * @param userName
	 * @return
	 */
	public Boolean checkUserName(String userName);
	
	/**
	 * Check if the given email address already exists
	 * @param userName
	 * @return
	 */
	public Boolean checkEmailAddress(String emailAddress);
	
	/**
	 * Find a particular category by its name
	 * @param categoryName
	 * @return
	 */
	public ProfileDetail findByEmailAddress(String emailAddress);
}
