package com.lesco.diccionario.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lesco.diccionario.model.City;

public class CityDAOImpl implements CityDAO {
	
	private SessionFactory sessionFactory;

	public CityDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Transactional
	public List<City> list() {
		
		@SuppressWarnings("unchecked")
        List<City> listCities = (List<City>) sessionFactory.getCurrentSession()
                .createCriteria(City.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listCities;
	}
}
