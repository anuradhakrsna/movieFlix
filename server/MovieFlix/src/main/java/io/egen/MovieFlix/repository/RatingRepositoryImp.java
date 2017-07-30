package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Ratings;

@Repository
public class RatingRepositoryImp implements RatingRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ratings> findAll() {
		TypedQuery<Ratings> query = em.createNamedQuery("Ratings.findAll", Ratings.class);
		List<Ratings> ratings = query.getResultList();
		return ratings;
	}

	@Override
	public Ratings findById(String id) {
		return em.find(Ratings.class, id);
	}

	@Override
	public Ratings findByRating(String rating) {
		TypedQuery<Ratings> query = em.createNamedQuery("Ratings.findByRating", Ratings.class);
		query.setParameter("pRating", rating);
		List<Ratings> ratings = query.getResultList();

		if (ratings != null && ratings.size() == 1) {
			return ratings.get(0);
		}

		return null;
	}

	@Override
	public Ratings create(Ratings rating) {
		em.persist(rating);
		return rating;
	}

	@Override
	public Ratings update(Ratings rating) {
		return em.merge(rating);
	}

	@Override
	public void delete(Ratings rating) {
		em.remove(rating);
	}

}
