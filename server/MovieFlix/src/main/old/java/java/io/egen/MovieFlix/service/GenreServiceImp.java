package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Genre;
import io.egen.MovieFlix.exception.GenreAlreadyExistsException;
import io.egen.MovieFlix.exception.GenreIdMismatchException;
import io.egen.MovieFlix.exception.GenreNotFoundException;
import io.egen.MovieFlix.repository.GenreRepository;

@Service
public class GenreServiceImp implements GenreService {

	@Autowired
	GenreRepository repository;

	@Override
	public List<Genre> findAll() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public Genre create(Genre genre) {

		if (null != repository.findByGenre(genre.getGenre())) {
			throw new GenreAlreadyExistsException("Email already in use : " + genre.getGenre());
		}

		return repository.create(genre);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Genre existing = repository.findById(id);

		if (null == existing) {
			throw new GenreNotFoundException("Genre with id : " + id + " not found");
		}

		repository.delete(existing);
	}

	@Override
	@Transactional
	public Genre update(String id, Genre genre) {
		
		Genre existing = repository.findById(id);
		
		if(null == existing) {
			throw new GenreNotFoundException("Genre with id : " + id + " not found"); 
		}
		
		//To prevent creation of new object if JSON object Id is not provided
		//And to prevent update of the wrong object if Id is not matching
		if(!id.equals(genre.getId())) {
			throw new GenreIdMismatchException("Request id : " + id + " does not match the object id : " + genre.getId()); 
		}
		
		return repository.update(genre);
	}

	@Override
	public Genre findById(String id) {
		
		Genre existing = repository.findById(id);
		if(null == existing) {
			throw new GenreNotFoundException("Genre with id : " + id + " not found"); 
		}
		
		return existing;
	}

}
