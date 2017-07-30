package io.egen.MovieFlix.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserRatings;
import io.egen.MovieFlix.entity.Users;

@Repository
public class UserRatingsRepositoryImp implements UserRatingsRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UserRatings> findAll() {
		TypedQuery<UserRatings> query = em.createNamedQuery("UserRatings.findAll", UserRatings.class);
		return query.getResultList();
	}

	@Override
	public UserRatings findById(String id) {
		return em.find(UserRatings.class, id);
	}

	@Override
	public UserRatings create(UserRatings uratings) {
		em.persist(uratings);
		return uratings;
	}

	@Override
	public UserRatings update(UserRatings uratings) {
		return em.merge(uratings);
	}

	@Override
	public void delete(UserRatings uratings) {
		em.remove(uratings);
	}

	@Override
	public UserRatings findRating(Users user, Movies movie) {
		TypedQuery<UserRatings> query = em.createNamedQuery("UserRatings.findUratingsByMovie", UserRatings.class);
		query.setParameter("pUsers", user);
		query.setParameter("pMovies", movie);
		List<UserRatings> ur = query.getResultList();
		if (ur != null && ur.size() == 1) {
			return ur.get(0);
		}
		return null;
	}

	@Override
	public List<String> findStats(Movies movie) {
		@SuppressWarnings("unchecked")
		List<Object[]> list = em
				.createQuery("SELECT CAST(AVG(ur.uratings) AS string), "
						+ "CAST(COUNT(ur.uratings) AS string) FROM UserRatings ur WHERE ur.movies=:pMovies")
				.setParameter("pMovies", movie).getResultList();

		List<String> ret = new ArrayList<String>();

		if (list == null) {
			ret.add("0");
			ret.add("0");
		} else {
			ret.add((String) list.get(0)[0]);
			ret.add((String) list.get(0)[1]);
		}

		return ret;
	}

}
