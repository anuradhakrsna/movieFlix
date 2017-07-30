package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Genres;

@Repository
public class GenreRepositoryImp implements GenreRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Genres> findAll() {
		TypedQuery<Genres> query = em.createNamedQuery("Genres.findAll", Genres.class);
		List<Genres> genres = query.getResultList();
		return genres;
	}

	@Override
	public Genres findById(String id) {
		return em.find(Genres.class, id);
	}

	@Override
	public Genres findByGenre(String genre) {
		TypedQuery<Genres> query = em.createNamedQuery("Genres.findByGenre", Genres.class);
		query.setParameter("pGenre", genre);
		List<Genres> genres = query.getResultList();

		if (genres != null && genres.size() == 1) {
			return genres.get(0);
		}

		return null;
	}

	@Override
	public Genres create(Genres genre) {
		em.persist(genre);
		return genre;
	}

	@Override
	public Genres update(Genres genre) {

		return em.merge(genre);
	}

	@Override
	public void delete(Genres genre) {
		em.remove(genre);
	}
}
