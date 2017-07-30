package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Directors;

@Repository
public class DirectorRepositoryImp implements DirectorRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Directors> findAll() {
		TypedQuery<Directors> query = em.createNamedQuery("Directors.findAll", Directors.class);
		List<Directors> directors = query.getResultList();
		return directors;
	}

	@Override
	public Directors findById(String Id) {
		return em.find(Directors.class, Id);
	}

	@Override
	public Directors findByDirector(String director) {
		TypedQuery<Directors> query = em.createNamedQuery("Directors.findByDirector", Directors.class);
		query.setParameter("pdirector", director);
		List<Directors> directors = query.getResultList();

		if (directors != null && directors.size() == 1) {
			return directors.get(0);
		}

		return null;
	}

	@Override
	public Directors create(Directors director) {
		em.persist(director);
		return director;
	}

	@Override
	public Directors update(Directors director) {
		return em.merge(director);
	}

	@Override
	public void delete(Directors director) {
		em.remove(director);
	}

}
