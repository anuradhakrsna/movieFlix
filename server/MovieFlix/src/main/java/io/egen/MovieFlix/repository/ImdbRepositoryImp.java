package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Imdb;

@Repository
public class ImdbRepositoryImp implements ImdbRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Imdb> findAll() {
		TypedQuery<Imdb> query = em.createNamedQuery("Imdb.findAll", Imdb.class);
		List<Imdb> imdbs = query.getResultList();
		return imdbs;
	}

	@Override
	public Imdb findById(String id) {
		return em.find(Imdb.class, id);
	}

	@Override
	public Imdb findByImdbId(String imdbId) {
		TypedQuery<Imdb> query = em.createNamedQuery("Imdb.findByImdbId", Imdb.class);
		query.setParameter("pimdbId", imdbId);
		List<Imdb> imdbs = query.getResultList();

		if (imdbs != null && imdbs.size() == 1) {
			return imdbs.get(0);
		}

		return null;
	}

	@Override
	public Imdb create(Imdb imdb) {
		em.persist(imdb);
		return imdb;
	}

	@Override
	public Imdb update(Imdb imdb) {
		return em.merge(imdb);
	}

	@Override
	public void delete(Imdb imdb) {
		em.remove(imdb);
	}

}
