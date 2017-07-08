package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Category;

/**
 * Category Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public interface CategoryDAO {
	
	/**
	 * Saves a new category
	 * @param category
	 */
	public void save(Category category);
	
	/**
	 * Deletes a category
	 * 
	 * @param categoryId
	 * @return
	 */
	public Boolean deleteById(Integer categoryId);

	/**
	 * Get a list of all categories
	 * @return
	 */
	public List<Category> list();
	
	/**
	 * Find a particular category by its name
	 * @param categoryName
	 * @return
	 */
	public Category findByCategoryName(String categoryName);
}
