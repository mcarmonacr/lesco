package com.lesco.diccionario.model;

/**
 * Video POJO
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class Video {

	private Integer videoId;
	private String termYoutubeVideoID;
	private String definitionYoutubeVideoID;
	private String explanationYoutubeVideoID;
	private String exampleYoutubeVideoID;
	    
	//Foreign Key
	private Word word;

	
	/**
	 * @return the videoId
	 */
	public Integer getVideoId() {
		return videoId;
	}

	/**
	 * @param videoId the videoId to set
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	/**
	 * @return the termYoutubeVideoID
	 */
	public String getTermYoutubeVideoID() {
		return termYoutubeVideoID;
	}

	/**
	 * @param termYoutubeVideoID the termYoutubeVideoID to set
	 */
	public void setTermYoutubeVideoID(String termYoutubeVideoID) {
		this.termYoutubeVideoID = termYoutubeVideoID;
	}

	/**
	 * @return the definitionYoutubeVideoID
	 */
	public String getDefinitionYoutubeVideoID() {
		return definitionYoutubeVideoID;
	}

	/**
	 * @param definitionYoutubeVideoID the definitionYoutubeVideoID to set
	 */
	public void setDefinitionYoutubeVideoID(String definitionYoutubeVideoID) {
		this.definitionYoutubeVideoID = definitionYoutubeVideoID;
	}

	/**
	 * @return the explanationYoutubeVideoID
	 */
	public String getExplanationYoutubeVideoID() {
		return explanationYoutubeVideoID;
	}

	/**
	 * @param explanationYoutubeVideoID the explanationYoutubeVideoID to set
	 */
	public void setExplanationYoutubeVideoID(String explanationYoutubeVideoID) {
		this.explanationYoutubeVideoID = explanationYoutubeVideoID;
	}

	/**
	 * @return the exampleYoutubeVideoID
	 */
	public String getExampleYoutubeVideoID() {
		return exampleYoutubeVideoID;
	}

	/**
	 * @param exampleYoutubeVideoID the exampleYoutubeVideoID to set
	 */
	public void setExampleYoutubeVideoID(String exampleYoutubeVideoID) {
		this.exampleYoutubeVideoID = exampleYoutubeVideoID;
	}

	/**
	 * @return the word
	 */
	public Word getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(Word word) {
		this.word = word;
	}

}
