package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Imdb;

public interface ImdbService {

	public List<Imdb> findAll();

	public Imdb findById(String id);

	public Imdb create(Imdb imdb);

	public Imdb update(String id, Imdb imdb);

	public void delete(String id);
}
