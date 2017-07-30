package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Movies;

@Repository
public class MovieRepositoryImp implements MovieRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Movies> findAll() {
		TypedQuery<Movies> query = em.createNamedQuery("Movie.findAll", Movies.class);
		List<Movies> movies = query.getResultList();
		return movies;
	}

	@Override
	public Movies findById(String id) {
		TypedQuery<Movies> query = em.createNamedQuery("Movie.findById", Movies.class);
		query.setParameter("pId", id);
		List<Movies> movies = query.getResultList();

		if (movies != null && movies.size() == 1) {
			return movies.get(0);
		}

		return null;
	}

	@Override
	public List<Movies> findByFilter(String type, int year, String genre) {

		String queryString = "SELECT DISTINCT m FROM Movies m ";
		if (type != null) {
			queryString += " JOIN m.type t ";
		}
		if (genre != null) {
			queryString += " JOIN m.genre g ";
		}

		// Dummy entry to use AND later on
		queryString += " WHERE 1 = 1 ";

		if (type != null) {
			queryString += " AND t.type = :pType";
		}
		if (genre != null) {
			queryString += " AND g.genre = :pGenre";
		}
		if (year != 0) {
			queryString += " AND m.year = :pYear";
		}

		TypedQuery<Movies> query = em.createQuery(queryString, Movies.class);

		if (type != null) {
			query.setParameter("pType", type);
		}
		if (genre != null) {
			query.setParameter("pGenre", genre);
		}
		if (year != 0) {
			query.setParameter("pYear", year);
		}

		List<Movies> movies = query.getResultList();
		return movies;
	}

	@Override
	public Movies create(Movies movie) {
		em.persist(movie);
		return movie;
	}

	@Override
	public Movies update(Movies movie) {
		return em.merge(movie);
	}

	@Override
	public void delete(Movies movie) {
		em.remove(movie);
	}

	@Override
	public List<Movies> findTopRated(String type) {
		TypedQuery<Movies> query = em.createNamedQuery("Movie.topRatedShows", Movies.class);
		query.setParameter("pType", type);
		List<Movies> movies = query.getResultList();
		return movies;
	}

}
