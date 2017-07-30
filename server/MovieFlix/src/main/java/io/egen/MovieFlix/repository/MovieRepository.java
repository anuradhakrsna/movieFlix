package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Movies;

public interface MovieRepository {

	public List<Movies> findAll();

	public Movies findById(String id);

	public List<Movies> findByFilter(String type, int year, String genre);

	public List<Movies> findTopRated(String type);

	public Movies create(Movies movie);

	public Movies update(Movies movie);

	public void delete(Movies movie);

}
