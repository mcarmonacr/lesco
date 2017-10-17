package com.lesco.diccionario.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Category POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class Category {

	private Integer categoryId;
	private String categoryName;
	
	//Foreign key
	private Set<Word> words = new HashSet<Word>(0); 
	
	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * @return the words
	 */
	public Set<Word> getWords() {
		return words;
	}
	/**
	 * @param words the words to set
	 */
	public void setWords(Set<Word> words) {
		this.words = words;
	}
	
}
