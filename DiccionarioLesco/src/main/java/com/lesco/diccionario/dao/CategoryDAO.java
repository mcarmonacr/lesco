package com.lesco.diccionario.dao;

import java.util.List;

import com.lesco.diccionario.modelo.Category;

public interface CategoryDAO {
	
	public void save(Category category);

	public List<Category> list();
	
	public Category findByCategoryName(String categoryName);
}
