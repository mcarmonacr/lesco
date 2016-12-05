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
	private String directotyPath;
	private String url;
	private String videoType;
	
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
	 * @return the directotyPath
	 */
	public String getDirectotyPath() {
		return directotyPath;
	}

	/**
	 * @param directotyPath the directotyPath to set
	 */
	public void setDirectotyPath(String directotyPath) {
		this.directotyPath = directotyPath;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the videoType
	 */
	public String getVideoType() {
		return videoType;
	}

	/**
	 * @param videoType the videoType to set
	 */
	public void setVideoType(String videoType) {
		this.videoType = videoType;
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
