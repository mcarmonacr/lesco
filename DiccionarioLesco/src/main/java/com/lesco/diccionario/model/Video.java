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
	private String youtubeVideoID;
	
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
	 * @return the youtubeVideoID
	 */
	public String getYoutubeVideoID() {
		return youtubeVideoID;
	}

	/**
	 * @param youtubeVideoID the youtubeVideoID to set
	 */
	public void setYoutubeVideoID(String youtubeVideoID) {
		this.youtubeVideoID = youtubeVideoID;
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
