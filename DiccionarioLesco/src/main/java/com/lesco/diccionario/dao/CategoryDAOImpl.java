package com.lesco.diccionario.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.controller.RegisterController;
import com.lesco.diccionario.model.Category;

/**
 * Category Table Data Access Object Implementation
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class CategoryDAOImpl implements CategoryDAO {
	
	//Session factory injection
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(CategoryDAOImpl.class);
	
	/**
	 * Saves a new category
	 */
	@Transactional
	public void save(Category category) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(category);
		tx.commit();
		session.close();
	}
	
	/**
	 * Get a list of all categories
	 */
	@Transactional
	public List<Category> list() {
		
		@SuppressWarnings("unchecked")
        List<Category> listCategories = (List<Category>) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listCategories;
	}
	
	/**
	 * Find a particular category by its name
	 */
	@Transactional
	public Category findByCategoryName(String categoryName){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("categoryName",categoryName));
        return (Category) criteria.uniqueResult();
		
	}
}
