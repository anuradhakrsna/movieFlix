package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Imdb;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.ImdbRepository;

@Service
public class ImdbServiceImp implements ImdbService {

	@Autowired
	ImdbRepository repository;

	@Override
	public List<Imdb> findAll() {
		return repository.findAll();
	}

	@Override
	public Imdb findById(String id) {

		Imdb existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Imdb with id : " + id + " not found");
		}

		return repository.findById(id);
	}

	@Override
	@Transactional
	public Imdb create(Imdb imdb) {

		if (null != repository.findByImdbId(imdb.getImdbId())) {
			throw new AlreadyExistsException("Imdb " + imdb.getImdbId() + " already exists");
		}

		return repository.create(imdb);
	}

	@Override
	@Transactional
	public Imdb update(String id, Imdb imdb) {

		Imdb existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Imdb with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(imdb.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + imdb.getId());
		}

		return repository.update(imdb);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Imdb existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Imdb with id : " + id + " not found");
		}

		repository.delete(existing);
	}
}
