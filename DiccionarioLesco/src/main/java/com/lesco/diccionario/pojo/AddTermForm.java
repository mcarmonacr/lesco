/**
 * 
 */
package com.lesco.diccionario.pojo;

/**
 * RegisterForm POJO
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
	private String youtubeType;
	private String fileType;
	private String videoURL;
	private Boolean filePath;
	
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
	/**
	 * @return the youtubeType
	 */
	public String getYoutubeType() {
		return youtubeType;
	}
	/**
	 * @param youtubeType the youtubeType to set
	 */
	public void setYoutubeType(String youtubeType) {
		this.youtubeType = youtubeType;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the videoURL
	 */
	public String getVideoURL() {
		return videoURL;
	}
	/**
	 * @param videoURL the videoURL to set
	 */
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	/**
	 * @return the filePath
	 */
	public Boolean getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(Boolean filePath) {
		this.filePath = filePath;
	}

}
