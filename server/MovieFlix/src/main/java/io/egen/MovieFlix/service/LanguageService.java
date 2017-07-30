package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Languages;;

public interface LanguageService {

	public List<Languages> findAll();

	public Languages findById(String id);

	public Languages create(Languages language);

	public Languages update(String id, Languages language);

	public void delete(String id);

}
