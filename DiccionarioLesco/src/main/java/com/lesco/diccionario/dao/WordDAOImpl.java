package com.lesco.diccionario.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.controller.AboutController;
import com.lesco.diccionario.model.Category;
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
	
	/**
	 * Find a particular category by its name
	 */
	@Transactional
	public Word findByWordName(String wordName){
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
        criteria.add(Restrictions.eq("wordName",wordName));
        return (Word) criteria.uniqueResult();
		
	}
}
