package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Word;

/**
 * Word Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

public interface WordDAO {
	
	/**
	 * Saves a new word
	 * @param word
	 */
	public void save(Word word);
	
	/**
	 * Delete a particular word
	 * 
	 * @param wordId
	 * @return
	 */
	public Boolean deleteById(Integer wordId);

	/**
	 * Get a list of all words
	 * @return
	 */
	public List<Word> list();
	
	/**
	 * Find a particular word by its name
	 * @param wordName
	 * @return
	 */
	public Word findByWordName(String wordName);
	
	/**
	 * Find a particular Word by its id
	 * 
	 * @param wordId
	 * @return
	 */
	public Word findById(Integer wordId);
	
	/**
	 * List of words of a particular user
	 * 
	 * @param UserProfile_ID
	 * @return
	 */
	public List<Word> findByUser(Integer userProfile_ID);
	
	/**
	 * Get a list of all the matching words
	 * @return
	 */
	public List<Word> findByPattern(String termsInput);
	
	/**
	 * Get a list of all the matching words of a particular user
	 * @return
	 */
	public List<Word> findByUserAndPattern(Integer userProfile_ID, String termsInput);
	
	/**
	 * Get a list of all the matching words and the given category ID
	 * @return
	 */
	public List<Word> findByPatternAndCategoryId(String termsInput, Integer categoryId);
	
	/**
	 * Get a list of all the matching words and the given category ID of a particular user
	 * @return
	 */
	public List<Word> findByUserPatternAndCategoryId(Integer userProfile_ID, String termsInput, Integer categoryId);
	
	/**
	 * Check if the given word name name already exists
	 * @param wordName
	 * @return
	 */
	public Boolean checkWordName(String wordName);

}
