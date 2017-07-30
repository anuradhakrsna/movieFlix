package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Actors;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.ActorRepository;

@Service
public class ActorServiceImp implements ActorService {

	@Autowired
	ActorRepository repository;

	@Override
	public List<Actors> findAll() {
		return repository.findAll();
	}

	@Override
	public Actors findById(String id) {

		Actors existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Actor with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public Actors create(Actors Actor) {

		if (null != repository.findByActor(Actor.getActor())) {
			throw new AlreadyExistsException("Actor " + Actor.getActor() + " already exists");
		}

		return repository.create(Actor);
	}

	@Override
	@Transactional
	public Actors update(String id, Actors Actor) {

		Actors existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Actor with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(Actor.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + Actor.getId());
		}

		return repository.update(Actor);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Actors existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Actor with id : " + id + " not found");
		}
		repository.delete(existing);
	}

}
