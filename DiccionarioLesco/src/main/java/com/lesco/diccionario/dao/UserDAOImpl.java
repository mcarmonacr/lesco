package com.lesco.diccionario.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.modelo.UserProfile;

public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Transactional
	public void save(UserProfile userProfile) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(userProfile);
		tx.commit();
		session.close();
	}
	
//	@Transactional
//	public List<Category> list() {
//		
//		@SuppressWarnings("unchecked")
//        List<Category> listCategories = (List<Category>) sessionFactory.getCurrentSession()
//                .createCriteria(Category.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//
//        return listCategories;
//	}
	
	@Transactional
	public UserProfile findByUserName(String userName){
		
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
//        criteria.add(Restrictions.eq("userName",userName));
//        return (UserProfile) criteria.uniqueResult();
        
        
        Query query = sessionFactory.getCurrentSession().createQuery("from UserProfile where userName = :userName ");
        query.setParameter("userName", userName);
        List list = query.list();
        
		return null;
	}
}
