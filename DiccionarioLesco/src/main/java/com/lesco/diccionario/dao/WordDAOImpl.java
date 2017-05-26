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
import com.lesco.diccionario.model.Word;

/**
 * Category Table Data Access Object Implementation
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class WordDAOImpl implements WordDAO {
	
	//Session factory injection
	private SessionFactory sessionFactory;

	public WordDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(WordDAOImpl.class);
	
	/**
	 * Saves a new word
	 */
	@Transactional
	public void save(Word word) {
		
		logger.debug("WordDAOImpl - save() - Start");
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(word);
		tx.commit();
		session.close();
		
		logger.debug("WordDAOImpl - save() - End");
	}
	
	/**
	 * Get a list of all words
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Word> list() {
		
		logger.debug("WordDAOImpl - List<Word>() - Start");

        List<Word> listWords = (List<Word>) sessionFactory.getCurrentSession()
                .createCriteria(Word.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		logger.debug("WordDAOImpl - List<Word>() - End");
		
        return listWords;
	}
	
	/**
	 * Find words that match the pattern
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> findByPattern(String termsInput){
		
		logger.debug("WordDAOImpl - findByPattern() - Start");
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Word where wordName like :wordName Order By wordName");
        query.setParameter("wordName", termsInput + "%");
        List<Word> wordsList= query.list();
        
        logger.debug("WordDAOImpl - findByPattern() - End");
        
        return wordsList;
	}
	
	/**
	 * Find words that match the pattern and also the category
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> findByPatternAndCategoryId(String termsInput, Integer categoryId){
		
		logger.debug("WordDAOImpl - findByPatternAndCategoryId() - Start");
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Word where wordName like :wordName and Category_ID =:categoryId Order By wordName");
        query.setParameter("wordName", termsInput + "%");
        query.setParameter("categoryId", categoryId);
        List<Word> wordsList= query.list();
        
        logger.debug("WordDAOImpl - findByPatternAndCategoryId() - End");
        
        return wordsList;
	}
	
	/**
	 * Find a particular word by its name
	 */
	@Transactional
	public Word findByWordName(String wordName){
		
		logger.debug("WordDAOImpl - findByWordName() - Start");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
		//criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("wordName",wordName));
        
        logger.debug("WordDAOImpl - findByWordName() - End");
        
        return (Word) criteria.uniqueResult();
	}
	
	
	/**
	 * Find a particular word by its id
	 */
	@Transactional
	public Word findById(Integer wordId){
		
		logger.debug("WordDAOImpl - findById() - Start");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
		criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("wordId",wordId));
        
        logger.debug("WordDAOImpl - findById() - End");
        
        return (Word) criteria.uniqueResult();      
	}
	
	/**
	 * Check if the given word name already exists in the database
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean checkWordName(String wordName){
		
		logger.debug("WordDAOImpl - checkWordName() - Start");
		
		Boolean result;
		
        Query query = sessionFactory.getCurrentSession().createQuery("from Word where wordName = :wordName");
        query.setParameter("wordName", wordName);
        List<UserProfile> requestList= query.list();

        if(!requestList.isEmpty()) {      
        	result= true;
        } else {
        	result= false;
        }
        
        logger.debug("WordDAOImpl - checkWordName() - End");
        
        return result;
	}
}
