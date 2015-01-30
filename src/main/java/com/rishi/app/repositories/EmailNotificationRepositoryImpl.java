package com.rishi.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.EmailNotification;

@Repository
public class EmailNotificationRepositoryImpl implements EmailNotificationRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public EmailNotification create(EmailNotification p) {
		em.persist(p);
		return p;
	}

	@Override
	@Transactional
	public EmailNotification delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public EmailNotification find(Long id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<EmailNotification> criteriaQuery = criteriaBuilder.createQuery(EmailNotification.class);
		Root<EmailNotification> root = criteriaQuery.from( EmailNotification.class );
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List <EmailNotification> result = em.createQuery( criteriaQuery ).getResultList();
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public EmailNotification update(EmailNotification p) {
		em.merge(p);
		return p;
	}
}
