package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Countries;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.CountryRepository;

@Service
public class CountryServiceImp implements CountryService {

	@Autowired
	CountryRepository repository;

	@Override
	public List<Countries> findAll() {
		return repository.findAll();
	}

	@Override
	public Countries findById(String id) {

		Countries existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Country with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public Countries create(Countries director) {

		if (null != repository.findByCountry(director.getCountry())) {
			throw new AlreadyExistsException("Country " + director.getCountry() + " already exists");
		}

		return repository.create(director);
	}

	@Override
	@Transactional
	public Countries update(String id, Countries director) {

		Countries existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Country with id : " + id + " not found");
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
		Countries existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Country with id : " + id + " not found");
		}
		repository.delete(existing);
	}

}
