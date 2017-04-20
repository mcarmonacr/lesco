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

import com.lesco.diccionario.controller.AboutController;
import com.lesco.diccionario.model.Category;
import com.lesco.diccionario.model.ProfileDetail;
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
	 * Saves a new category
	 */
	@Transactional
	public void save(Word word) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(word);
		tx.commit();
		session.close();
	}
	
	/**
	 * Get a list of all categories
	 */
	@Transactional
	public List<Word> list() {
		
		@SuppressWarnings("unchecked")
        List<Word> listWords = (List<Word>) sessionFactory.getCurrentSession()
                .createCriteria(Word.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listWords;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> findByPattern(String termsInput){
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Word where wordName like :wordName Order By wordName");
        query.setParameter("wordName", termsInput + "%");
        List<Word> wordsList= query.list();
        
        return wordsList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Word> findByPatternAndCategoryId(String termsInput, Integer categoryId){
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Word where wordName like :wordName and Category_ID =:categoryId Order By wordName");
        query.setParameter("wordName", termsInput + "%");
        query.setParameter("categoryId", categoryId);
        List<Word> wordsList= query.list();
        
        return wordsList;
	}
	
	/**
	 * Find a particular category by its name
	 */
	@Transactional
	public Word findByWordName(String wordName){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
		//criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("wordName",wordName));
        return (Word) criteria.uniqueResult();
		
	}
	
	
	/**
	 * Find a particular word by its id
	 */
	@Transactional
	public Word findById(Integer wordId){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
		criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("wordId",wordId));
        return (Word) criteria.uniqueResult();
		       
	}
}
