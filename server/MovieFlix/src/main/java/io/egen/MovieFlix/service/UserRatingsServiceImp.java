package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserRatings;
import io.egen.MovieFlix.entity.Users;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.MovieRepository;
import io.egen.MovieFlix.repository.UserRatingsRepository;
import io.egen.MovieFlix.repository.UserRepository;

@Service
public class UserRatingsServiceImp implements UserRatingsService {

	@Autowired
	UserRatingsRepository repository;

	@Autowired
	UserRepository userrepository;

	@Autowired
	MovieRepository movierepository;

	@Override
	public List<UserRatings> findAll() {
		return repository.findAll();
	}

	@Override
	public UserRatings findById(String id) {
		UserRatings existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("UserRatings with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public UserRatings create(UserRatings uratings) {
		Users existingUser = userrepository.findById(uratings.getUsers().getId());
		if (existingUser == null) {
			throw new NotFoundException("User with id : " + uratings.getUsers().getId() + " not found");
		}
		uratings.setUsers(existingUser);

		Movies existingMovies = movierepository.findById(uratings.getMovies().getId());

		if (existingMovies == null) {
			throw new NotFoundException("Movie with id : " + uratings.getMovies().getId() + " not found");
		}
		uratings.setMovies(existingMovies);

		return repository.create(uratings);
	}

	@Override
	@Transactional
	public UserRatings update(String id, UserRatings uratings) {

		UserRatings existing = repository.findById(id);
		if (!id.equals(uratings.getId())) {
			throw new MismatchException("Id Mismatch");
		}
		// for not allowing the user to modify the predefined id's
		uratings.setUsers(existing.getUsers());
		uratings.setMovies(existing.getMovies());

		return repository.update(uratings);
	}

	@Override
	@Transactional
	public void delete(String id) {
		UserRatings existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("UserRatings with id " + id + " not found");
		}
		repository.delete(existing);

	}

	@Override
	@Transactional
	public UserRatings findRating(String userId, String movieId) {

		Users existingUser = userrepository.findById(userId);
		if (existingUser == null) {
			throw new NotFoundException("User with id : " + userId + " not found");
		}

		Movies existingMovies = movierepository.findById(movieId);
		if (existingMovies == null) {
			throw new NotFoundException("Movie with id : " + movieId + " not found");
		}
		return repository.findRating(existingUser, existingMovies);
	}

	@Override
	@Transactional
	public List<String> findStats(String movieId) {

		Movies existingMovies = movierepository.findById(movieId);
		if (existingMovies == null) {
			throw new NotFoundException("Movie with id : " + movieId + " not found");
		}

		return repository.findStats(existingMovies);
	}

}
