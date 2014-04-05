package com.rishi.app.repositories.post;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.Post;
import com.rishi.app.models.User;

@Repository
public class PostRepositoryImpl implements PostRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Post create(Post p) {
		em.persist(p);
		return p;
	}

	@Override
	@Transactional
	public Post delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> findPostsByUser(User u) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
		Root<Post> postRoot = criteriaQuery.from( Post.class );
		criteriaQuery.select(postRoot);
		criteriaQuery.where(criteriaBuilder.equal(postRoot.get("user"), u));
		return em.createQuery( criteriaQuery ).getResultList();
	}

	@Override
	public Post edit(Post p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Post find(Long id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
		Root<Post> postRoot = criteriaQuery.from( Post.class );
		criteriaQuery.select(postRoot);
		criteriaQuery.where(criteriaBuilder.equal(postRoot.get("id"), id));
		List <Post> result = em.createQuery( criteriaQuery ).getResultList();
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public Post update(Post p) {
		em.merge(p);
		return p;
	}
}
