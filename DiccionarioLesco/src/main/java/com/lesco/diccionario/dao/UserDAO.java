package com.lesco.diccionario.dao;

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
	 * Find a particular user by its userName
	 * @param userName
	 * @return
	 */
	public Boolean findByUserName(String userName);
}
