package com.lesco.diccionario.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.modelo.Category;
import com.lesco.diccionario.modelo.City;

public class CategoryDAOImpl implements CategoryDAO {
	
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Transactional
	public List<Category> list() {
		
		@SuppressWarnings("unchecked")
        List<Category> listCategories = (List<Category>) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listCategories;
	}
}
