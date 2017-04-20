package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Word;

/**
 * Category Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

public interface WordDAO {
	
	/**
	 * Saves a new category
	 * @param category
	 */
	public void save(Word word);

	/**
	 * Get a list of all categories
	 * @return
	 */
	public List<Word> list();
	
	/**
	 * Find a particular category by its name
	 * @param categoryName
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
	 * Get a list of all the matching terms
	 * @return
	 */
	public List<Word> findByPattern(String termsInput);
	
	/**
	 * Get a list of all the matching terms and the given category ID
	 * @return
	 */
	public List<Word> findByPatternAndCategoryId(String termsInput, Integer categoryId);
	
	
}
