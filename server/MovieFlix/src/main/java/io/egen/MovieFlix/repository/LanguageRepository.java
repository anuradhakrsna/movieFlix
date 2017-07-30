package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Languages;

public interface LanguageRepository {
	public List<Languages> findAll();

	public Languages findById(String id);

	public Languages findByLanguage(String language);

	public Languages create(Languages language);

	public Languages update(Languages language);

	public void delete(Languages language);

}
