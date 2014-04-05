package com.rishi.app.repositories.user;


import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public User save(User user) {
		em.persist(user);
		return user;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findUserByEmail(String email){
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from( User.class );
		criteriaQuery.select(userRoot);
		criteriaQuery.where(criteriaBuilder.equal(userRoot.get("email"), email));
		List<User> existingUsers = em.createQuery( criteriaQuery ).getResultList();
		if(existingUsers.size() == 1) {
			return existingUsers.get(0);
		} else {
			return null;	
		}
	}
}
