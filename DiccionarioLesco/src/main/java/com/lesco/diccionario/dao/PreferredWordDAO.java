package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.PreferredWord;

/**
 * Preferred Word Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public interface PreferredWordDAO {
	
	/**
	 * Saves a new preferred word
	 * @param category
	 */
	public void save(PreferredWord preferredWord);

	/**
	 * Get a list of all preferred words
	 * @return
	 */
	public List<PreferredWord> list();
	
	/**
	 * Find a particular preferred word by user ID
	 * @param userId
	 * @return
	 */
	public List<PreferredWord> findByUser(Integer userId);
}
