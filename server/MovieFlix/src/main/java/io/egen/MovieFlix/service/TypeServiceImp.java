package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Types;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.TypeRepository;

@Service
public class TypeServiceImp implements TypeService {

	@Autowired
	TypeRepository repository;

	@Override
	public List<Types> findAll() {
		return repository.findAll();
	}

	@Override
	public Types findById(String id) {

		Types existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Type with id : " + id + " not found");
		}

		return repository.findById(id);
	}

	@Override
	@Transactional
	public Types create(Types type) {

		if (null != repository.findByType(type.getType())) {
			throw new AlreadyExistsException("Type " + type.getType() + " already exists");
		}

		return repository.create(type);
	}

	@Override
	@Transactional
	public Types update(String id, Types type) {

		Types existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Type with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(type.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + type.getId());
		}

		return repository.update(type);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Types existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Type with id : " + id + " not found");
		}

		repository.delete(existing);
	}

}
