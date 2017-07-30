package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserComments;
import io.egen.MovieFlix.entity.Users;

@Repository
public class UserCommentRepositoryImp implements UserCommentRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UserComments> findAll() {
		TypedQuery<UserComments> query = em.createNamedQuery("UserComments.findAll", UserComments.class);
		List<UserComments> comments = query.getResultList();
		return comments;
	}

	@Override
	public UserComments findById(String id) {
		return em.find(UserComments.class, id);
	}

	@Override
	public List<UserComments> findAllByMovie(Movies movies) {
		TypedQuery<UserComments> query = em.createNamedQuery("UserComments.findAllByMovie", UserComments.class);
		query.setParameter("pMovies", movies);
		List<UserComments> comments = query.getResultList();
		return comments;
	}

	@Override
	public List<UserComments> findAllByUser(Users users) {
		TypedQuery<UserComments> query = em.createNamedQuery("UserComments.findAllByUsers", UserComments.class);
		query.setParameter("pUsers", users);
		List<UserComments> comments = query.getResultList();
		return comments;
	}

	@Override
	public UserComments create(UserComments comments) {
		em.persist(comments);
		return comments;
	}

	@Override
	public void delete(UserComments comments) {
		em.remove(comments);

	}

}
