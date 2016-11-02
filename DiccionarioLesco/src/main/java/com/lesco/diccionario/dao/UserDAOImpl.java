package com.lesco.diccionario.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.model.UserProfile;

/**
 * User Table Data Access Object Implementation
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class UserDAOImpl implements UserDAO {
	
	//Session factory injection
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	/**
	 * Save new user to the DB
	 */
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
	
	/**
	 * Find a particular user by its userName
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public UserProfile findByUserName(String userName){
		
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
//        criteria.add(Restrictions.eq("userName",userName));
//        return (UserProfile) criteria.uniqueResult();
        
        
        Query query = sessionFactory.getCurrentSession().createQuery("from UserProfile where userName like :userName");
        query.setParameter("userName", userName + "%");
        List<UserProfile> userList= query.list();
        
		return null;
	}
}
