package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Users;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public List<Users> findAll() {
		return repository.findAll();
	}

	@Override
	public Users findbyId(String id) {
		Users existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("User with id= " + id + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Users create(Users user) {
		Users existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			throw new AlreadyExistsException(
					"the following User already exists with the email id = " + user.getEmail());
		}
		return repository.create(user);
	}

	@Override
	@Transactional
	public Users update(String id, Users user) {
		Users existing = repository.findById(id);
		if (existing == null) {
			throw new NotFoundException("User with id= " + id + " not found");
		}
		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Users existing = repository.findById(id);
		if (existing == null) {
			throw new NotFoundException("User with id= " + id + " not found");
		}

		repository.delete(existing);

	}

}
