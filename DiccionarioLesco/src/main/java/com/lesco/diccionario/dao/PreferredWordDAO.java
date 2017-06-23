package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.PreferredWord;
import com.lesco.diccionario.model.Word;

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
	 * deletes a preferred word
	 * @param category
	 */
	public void delete(PreferredWord preferredWord);

	/**
	 * Get a list of all preferred words
	 * @return
	 */
	public List<PreferredWord> list();
	
	/**
	 * Get a list of all preferred words
	 * @return
	 */
	public List<Word> listMyWords(Integer userId);
	
	/**
	 * Find a particular preferred word by user ID
	 * @param userId
	 * @return
	 */
	public List<PreferredWord> findByUser(Integer userId);
	
	/**
	 * Find a particular preferred word by word ID
	 * @param wordId
	 * @return
	 */
	public List<PreferredWord> findByWord(Integer wordId);
	
	/**
	 * Find a particular preferred word by word and user ID
	 * @param wordId
	 * @param userId
	 * @return
	 */
	public PreferredWord findByWordAndUser(Integer wordId, Integer userId);
	
	/**
	 * Find a particular preferred word by word and user ID
	 * @param wordId
	 * @param userId
	 * @return
	 */
	public PreferredWord findById(Integer preferredWordId);
	
	/**
	 * Get a list of all the matching words
	 * @return
	 */
	public List<Word> findByPattern(String termsInput, Integer userId);
	
	/**
	 * Get a list of all the matching words and the given category ID
	 * @return
	 */
	public List<Word> findByPatternAndCategoryId(String termsInput, Integer categoryId, Integer userId);
}
