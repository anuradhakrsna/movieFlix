package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Genre;

@Repository
public class GenreRepositoryImp implements GenreRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Genre> findAll() {
		TypedQuery<Genre> query = em.createNamedQuery("Genre.findAll", Genre.class);
		List<Genre> genres = query.getResultList();
		return genres;
	}

	@Override
	public Genre findById(String id) {
		return em.find(Genre.class, id);
	}

	@Override
	public Genre findByGenre(String genre) {
		TypedQuery<Genre> query = em.createNamedQuery("Genre.findByGenre", Genre.class);
		query.setParameter("pGenre", genre);
		List<Genre> genres = query.getResultList();

		if (genres != null && genres.size() == 1) {
			return genres.get(0);
		}

		return null;
	}

	@Override
	public Genre create(Genre genre) {
		em.persist(genre);
		return genre;
	}

	@Override
	public void delete(Genre genre) {
		em.remove(genre);
	}

	@Override
	public Genre update(Genre genre) {
		
		return em.merge(genre);
	}

}
