package com.lesco.diccionario.model;

/**
 * Word POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class Word {

	private Integer wordId;
	private String wordName;
	private String description;
	private String explanation;
	private String example;
	private Integer numberOfVisits;
	
	//Foreign Key
	private UserProfile userProfile;
	private Video video;
	private Category category;

	
	/**
	 * @return the wordName
	 */
	public String getWordName() {
		return wordName;
	}

	/**
	 * @return the wordId
	 */
	public Integer getWordId() {
		return wordId;
	}

	/**
	 * @param wordId the wordId to set
	 */
	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	/**
	 * @param wordName the wordName to set
	 */
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the numberOfVisits
	 */
	public Integer getNumberOfVisits() {
		return numberOfVisits;
	}

	/**
	 * @param numberOfVisits the numberOfVisits to set
	 */
	public void setNumberOfVisits(Integer numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}

	/**
	 * @return the userProfile
	 */
	public UserProfile getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * @return the video
	 */
	public Video getVideo() {
		return video;
	}

	/**
	 * @param video the video to set
	 */
	public void setVideo(Video video) {
		this.video = video;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
