/**
 * 
 */
package com.lesco.diccionario.pojo;

/**
 * AddTermForm POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class AddTermForm{

	private String wordName;
	private String categoryName;
	private String definition;
	private String explanation;
	private String example;
	
	/**
	 * @return the wordName
	 */
	public String getWordName() {
		return wordName;
	}
	/**
	 * @param wordName the wordName to set
	 */
	public void setWordName(String wordName) {
		this.wordName = wordName;
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
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}
	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	/**
	 * @return the example
	 */
	public String getExample() {
		return example;
	}
	/**
	 * @param example the example to set
	 */
	public void setExample(String example) {
		this.example = example;
	}

}
