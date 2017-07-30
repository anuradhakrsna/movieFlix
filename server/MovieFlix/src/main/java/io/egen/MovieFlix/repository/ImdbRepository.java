package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Imdb;

public interface ImdbRepository {
	public List<Imdb> findAll();

	public Imdb findById(String id);

	public Imdb findByImdbId(String imdbId);

	public Imdb create(Imdb imdb);

	public Imdb update(Imdb imdb);

	public void delete(Imdb imdb);
}
