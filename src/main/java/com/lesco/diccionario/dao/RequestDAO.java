package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.model.Request;

/**
 * Request Table Data Access Object Interface
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */

public interface RequestDAO {
	
	/**
	 * Saves a new Request
	 * @param request
	 */
	public void save(Request request);

	/**
	 * Get a list of all requests
	 * @return
	 */
	public List<Request> list();
	
	/**
	 * Find a particular request by its name
	 * @param requestName
	 * @return
	 */
	public Request findByWordName(String requestName);
	
	/**
	 * Find a particular Request by its id
	 * 
	 * @param wordId
	 * @return
	 */
	public Request findById(Integer requestId);
	
	/**
	 * Get a list of all the matching requests
	 * @return
	 */
	public List<Request> findByPattern(String requestInput);
	
	/**
	 * Check if the given word name name already exists
	 * @param wordName
	 * @return
	 */
	public Boolean checkWordName(String wordName);
	
	
	/**
	 * Delete a particular word by its name
	 * 
	 * @param wordName
	 * @return
	 */
	public Boolean deleteByWordName(String wordName);
}
