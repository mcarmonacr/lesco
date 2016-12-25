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

import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.ProfileDetail;
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
	
	
	/**
	 * Updates a user in the DB
	 */
	@Transactional
	public void update(UserProfile userProfile) {
		
		
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//session.persist(userProfile);
		session.update(userProfile);
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
	 * Check if the given user name already exists
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean checkUserName(String userName){
		
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserProfile.class);
//        criteria.add(Restrictions.eq("userName",userName));
//        return (UserProfile) criteria.uniqueResult();

		logger.debug("UserDAOImpl - checkUserName() - Start");
		
        Query query = sessionFactory.getCurrentSession().createQuery("from UserProfile where userName like :userName");
        query.setParameter("userName", userName + "%");
        List<UserProfile> userList= query.list();

        if(!userList.isEmpty()) {      
        	logger.debug("UserDAOImpl - checkUserName() - End");
        	return true;
        }
        else{
        	logger.debug("UserDAOImpl - checkUserName() - End");
        	return false;
        }
	}

	/**
	 * Check if the given email address already exists
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean checkEmailAddress(String emailAddress) {
		
		logger.debug("UserDAOImpl - checkEmailAddress() - Start");
		
        Query query = sessionFactory.getCurrentSession().createQuery("from ProfileDetail where email like :emailAddress");
        query.setParameter("emailAddress", emailAddress + "%");
        List<ProfileDetail> profileDetailList= query.list();

        if(!profileDetailList.isEmpty()) {      
        	logger.debug("UserDAOImpl - checkEmailAddress() - End");
        	return true;
        }
        else{
        	logger.debug("UserDAOImpl - checkEmailAddress() - End");
        	return false;
        }
	}
	
	
	/**
	 * Find a particular category by its name
	 */
	@Transactional
	public ProfileDetail findByEmailAddress(String emailAddress){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProfileDetail.class);
        criteria.add(Restrictions.eq("email",emailAddress));
        
        ProfileDetail tempProfile = (ProfileDetail) criteria.uniqueResult();
        
        
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ProfileDetail emp = (ProfileDetail) session.load(ProfileDetail.class, tempProfile.getProfileDetailId());
		System.out.println("Employee object loaded. " + emp);
		tx.commit();
		//session.close();
        
		return emp;
		
	}
}
