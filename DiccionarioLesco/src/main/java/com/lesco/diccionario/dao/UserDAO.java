package com.lesco.diccionario.dao;

import com.lesco.diccionario.model.UserProfile;

public interface UserDAO {
	
	public void save(UserProfile userProfile);

	//public List<Category> list();
	
	public UserProfile findByUserName(String userName);
}
