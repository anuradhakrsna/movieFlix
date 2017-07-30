package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Genres;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.GenreRepository;

@Service
public class GenreServiceImp implements GenreService {

	@Autowired
	GenreRepository repository;

	@Override
	public List<Genres> findAll() {

		return repository.findAll();
	}

	@Override
	public Genres findById(String id) {

		Genres existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Genre with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public Genres create(Genres genre) {

		if (null != repository.findByGenre(genre.getGenre())) {
			throw new AlreadyExistsException("Genre " + genre.getGenre() + " already exists");
		}

		return repository.create(genre);
	}

	@Override
	@Transactional
	public Genres update(String id, Genres genre) {

		Genres existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Genre with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(genre.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + genre.getId());
		}

		return repository.update(genre);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Genres existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Genre with id : " + id + " not found");
		}
		repository.delete(existing);
	}

}
