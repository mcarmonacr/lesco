package com.lesco.diccionario.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.modelo.Category;
import com.lesco.diccionario.modelo.City;

public class CategoryDAOImpl implements CategoryDAO {
	
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Transactional
	public void save(Category category) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(category);
		tx.commit();
		session.close();
	}
	
	@Transactional
	public List<Category> list() {
		
		@SuppressWarnings("unchecked")
        List<Category> listCategories = (List<Category>) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listCategories;
	}
	
	@Transactional
	public Category findByCategoryName(String categoryName){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("categoryName",categoryName));
        return (Category) criteria.uniqueResult();
		
	}
}
