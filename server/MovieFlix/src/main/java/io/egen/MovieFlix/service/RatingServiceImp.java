package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Ratings;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.RatingRepository;

@Service
public class RatingServiceImp implements RatingService {

	@Autowired
	RatingRepository repository;

	@Override
	public List<Ratings> findAll() {
		return repository.findAll();
	}

	@Override
	public Ratings findById(String id) {

		Ratings existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Rating with id : " + id + " not found");
		}

		return repository.findById(id);
	}

	@Override
	@Transactional
	public Ratings create(Ratings rating) {

		if (null != repository.findByRating(rating.getRating())) {
			throw new AlreadyExistsException("Rating " + rating.getRating() + " already exists");
		}

		return repository.create(rating);
	}

	@Override
	@Transactional
	public Ratings update(String id, Ratings rating) {

		Ratings existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Rating with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(rating.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + rating.getId());
		}

		return repository.update(rating);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Ratings existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Rating with id : " + id + " not found");
		}

		repository.delete(existing);
	}

}
