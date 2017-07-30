package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Languages;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.LanguageRepository;

@Service
public class LanguageServiceImp implements LanguageService {

	@Autowired
	LanguageRepository repository;

	@Override
	public List<Languages> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Languages findById(String id) {
		// TODO Auto-generated method stub
		Languages existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Language with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	@Transactional
	public Languages create(Languages language) {
		// TODO Auto-generated method stub
		if (null != repository.findByLanguage(language.getLanguage())) {
			throw new AlreadyExistsException("Language " + language.getLanguage() + " already exists");
		}

		return repository.create(language);
	}

	@Override
	@Transactional
	public Languages update(String id, Languages language) {
		// TODO Auto-generated method stub
		Languages existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Languages with id : " + id + " not found");
		}

		// To prevent creation of new object if JSON object Id is not provided
		// And to prevent update of the wrong object if Id is not matching
		if (!id.equals(language.getId())) {
			throw new MismatchException("Request id : " + id + " does not match the object id : " + language.getId());
		}

		return repository.update(language);
	}

	@Override
	@Transactional
	public void delete(String id) {
		// TODO Auto-generated method stub
		Languages existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Language with id : " + id + " not found");
		}
		repository.delete(existing);

	}

}
