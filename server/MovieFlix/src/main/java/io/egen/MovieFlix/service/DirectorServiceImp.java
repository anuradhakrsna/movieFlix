package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Directors;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.DirectorRepository;

@Service
public class DirectorServiceImp implements DirectorService {

	@Autowired
	DirectorRepository repository;

	@Override
	public List<Directors> findAll() {
		return repository.findAll();
	}

	@Override
	public Directors findById(String id) {

		Directors existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Director with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public Directors create(Directors director) {

		if (null != repository.findByDirector(director.getDirector())) {
			throw new AlreadyExistsException("Director " + director.getDirector() + " already exists");
		}

		return repository.create(director);
	}

	@Override
	@Transactional
	public Directors update(String id, Directors director) {

		Directors existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Director with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(director.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + director.getId());
		}

		return repository.update(director);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Directors existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Director with id : " + id + " not found");
		}
		repository.delete(existing);
	}

}
