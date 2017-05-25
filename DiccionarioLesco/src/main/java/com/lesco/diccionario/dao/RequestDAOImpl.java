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

import com.lesco.diccionario.model.Request;
import com.lesco.diccionario.model.Word;

/**
 * Request Table Data Access Object Implementation
 * 
 * @author Mario Alonso Carmona Dinarte
 * @email monacar89@hotmail.com
 * @since 2016
 *
 */
public class RequestDAOImpl implements RequestDAO {
	
	//Session factory injection
	private SessionFactory sessionFactory;

	public RequestDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//Log4J class logger instance
	private static final Logger logger = Logger.getLogger(RequestDAOImpl.class);
	
	/**
	 * Saves a new request
	 */
	@Transactional
	public void save(Request request) {
		
		logger.debug("RequestDAOImpl - save() - Start");
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(request);
		tx.commit();
		session.close();
		
		logger.debug("RequestDAOImpl - save() - End");
	}
	
	/**
	 * Get a list of all requests
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Request> list() {
		
		logger.debug("RequestDAOImpl - List<Request>() - Start");

        List<Request> listRequests = (List<Request>) sessionFactory.getCurrentSession()
                .createCriteria(Request.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		logger.debug("RequestDAOImpl - List<Request>() - End");
		
        return listRequests;
	}
	
	/**
	 * Find requests that match the pattern
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Request> findByPattern(String requestsInput){
		
		logger.debug("RequestDAOImpl - findByPattern() - Start");
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Request where wordName like :wordName Order By wordName");
        query.setParameter("wordName", requestsInput + "%");
        List<Request> requestsList= query.list();
        
        logger.debug("RequestDAOImpl - findByPattern() - End");
        
        return requestsList;
	}
		
	/**
	 * Find a particular request by its name
	 */
	@Transactional
	public Request findByWordName(String wordName){
		
		logger.debug("RequestDAOImpl - findByWordName() - Start");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Request.class);
		//criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("wordName",wordName));
        
        logger.debug("RequestDAOImpl - findByWordName() - End");
        
        return (Request) criteria.uniqueResult();
	}
	
	/**
	 * Find a particular request by its id
	 */
	@Transactional
	public Request findById(Integer requestId){
		
		logger.debug("RequestDAOImpl - findById() - Start");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Request.class);
		criteria.setCacheable(true); //Improves performance. The average time drops close to the level of calling get.
        criteria.add(Restrictions.eq("requestId",requestId));
        
        logger.debug("RequestDAOImpl - findById() - End");
        
        return (Request) criteria.uniqueResult();      
	}
}
