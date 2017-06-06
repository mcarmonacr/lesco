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
	 * Deletes a preferred word
	 */
	@Transactional
	public void delete(PreferredWord preferredWord) {
		logger.debug("PreferredWordDAOImpl - delete() - Start");
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(preferredWord);
		tx.commit();
		session.close();
		
		logger.debug("PreferredWordDAOImpl - delete() - End");
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
	
	/**
	 * Find a particular preferred word by word
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PreferredWord> findByWord(Integer wordId){
		
		logger.debug("PreferredWordDAOImpl - findByWord() - Start");        
        
        Query query = sessionFactory.getCurrentSession().createQuery("from PreferredWord where word_ID = :word_ID");
        query.setParameter("word_ID", wordId);
        List<PreferredWord> preferredWordsList= query.list();
        
        logger.debug("PreferredWordDAOImpl - findByWord() - End");
        
        return preferredWordsList;
	}
	
	/**
	 * Find a particular preferred word by word and user
	 */
	@Transactional
	public PreferredWord findByWordAndUser(Integer wordId, Integer userId){
		
		logger.debug("PreferredWordDAOImpl - findByWordAndUser() - Start");        
        
        Query query = sessionFactory.getCurrentSession().createQuery("from PreferredWord where word_ID = :word_ID and userProfile_ID = :userProfile_ID");
        query.setParameter("word_ID", wordId);
        query.setParameter("userProfile_ID", userId);
        List<PreferredWord> preferredWordsList= query.list();
                
        logger.debug("PreferredWordDAOImpl - findByWordAndUser() - End");
        
        //return (PreferredWord) criteria.uniqueResult();
        return preferredWordsList.get(0);
	}
	
	
	/**
	 * Find a particular preferred word by word and user
	 */
	@Transactional
	public PreferredWord findById(Integer preferredWordId){
		
		logger.debug("PreferredWordDAOImpl - findById() - Start");        

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PreferredWord.class);
        criteria.add(Restrictions.eq("preferredWordId",preferredWordId));
        
        logger.debug("PreferredWordDAOImpl - findById() - End");
        
        return (PreferredWord) criteria.uniqueResult();
	}
	
	/**
	 * Find words that match the pattern
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> findByPattern(String termsInput){
		
		logger.debug("WordDAOImpl - findByPattern() - Start");
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM PreferredWord pw, Word w WHERE pw.Word_ID=w.Word_ID and w.wordName like :wordName Order By w.wordName")
				.addEntity(Word.class);
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
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM PreferredWord pw, Word w WHERE pw.Word_ID=w.Word_ID and w.wordName like :wordName and w.Category_ID =:categoryId Order By w.wordName")
				.addEntity(Word.class);
        query.setParameter("wordName", termsInput + "%");
        query.setParameter("categoryId", categoryId);
        List<Word> wordsList= query.list();
        
        
        logger.debug("WordDAOImpl - findByPatternAndCategoryId() - End");
        
        return wordsList;
	}
}
