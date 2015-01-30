package com.rishi.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.ConfigurationProperty;

@Repository
public class ConfigurationPropertyRepositoryImpl implements ConfigurationPropertyRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public ConfigurationProperty create(ConfigurationProperty p) {
		em.persist(p);
		return p;
	}

	@Override
	@Transactional
	public ConfigurationProperty delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConfigurationProperty> findAllConfigurationProperties() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigurationProperty> criteriaQuery = criteriaBuilder.createQuery(ConfigurationProperty.class);
		Root<ConfigurationProperty> root = criteriaQuery.from( ConfigurationProperty.class );
		criteriaQuery.select(root);
		return em.createQuery( criteriaQuery ).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public ConfigurationProperty find(Long id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigurationProperty> criteriaQuery = criteriaBuilder.createQuery(ConfigurationProperty.class);
		Root<ConfigurationProperty> root = criteriaQuery.from( ConfigurationProperty.class );
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List <ConfigurationProperty> result = em.createQuery( criteriaQuery ).getResultList();
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public ConfigurationProperty update(ConfigurationProperty p) {
		em.merge(p);
		return p;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ConfigurationProperty findConfigurationPropertyByName(String name) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ConfigurationProperty> criteriaQuery = criteriaBuilder
				.createQuery(ConfigurationProperty.class);
		Root<ConfigurationProperty> root = criteriaQuery.from(ConfigurationProperty.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));
		List<ConfigurationProperty> applicationConfigurations = em.createQuery(criteriaQuery).getResultList();
		if(applicationConfigurations.size()==1) {
			return applicationConfigurations.get(0);
		} else {
			return null;
		}
	}
	
}
