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
import com.lesco.diccionario.model.PreferredWord;
import com.lesco.diccionario.model.UserProfile;
import com.lesco.diccionario.model.Word;

/**
 * Preferred Word Table Data Access Object Implementation
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class PreferredWordDAOImpl implements PreferredWordDAO {
	
	//Session factory injection
	private SessionFactory sessionFactory;

	public PreferredWordDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(PreferredWordDAOImpl.class);
	
	/**
	 * Saves a new preferred word
	 */
	@Transactional
	public void save(PreferredWord preferredWord) {
		logger.debug("PreferredWordDAOImpl - save() - Start");
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(preferredWord);
		tx.commit();
		session.close();
		
		logger.debug("PreferredWordDAOImpl - save() - End");
	}
	
	/**
	 * Get a list of all preferred words
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public List<PreferredWord> list() {
		
		logger.debug("PreferredWordDAOImpl - List<PreferredWord>() - Start");
		
        List<PreferredWord> listPreferredWords = (List<PreferredWord>) sessionFactory.getCurrentSession()
                .createCriteria(PreferredWord.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        logger.debug("PreferredWordDAOImpl - List<PreferredWord>() - End");
        
        return listPreferredWords;
	}
	
	/**
	 * Find a particular preferred word by user
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PreferredWord> findByUser(Integer userId){
		
		logger.debug("PreferredWordDAOImpl - findByUser() - Start");        
        
        Query query = sessionFactory.getCurrentSession().createQuery("from PreferredWord where userProfile_ID = :userProfile_ID");
        query.setParameter("userProfile_ID", userId);
        List<PreferredWord> preferredWordsList= query.list();
        
        
        logger.debug("PreferredWordDAOImpl - findByUser() - End");
        
        return preferredWordsList;
	}
}
