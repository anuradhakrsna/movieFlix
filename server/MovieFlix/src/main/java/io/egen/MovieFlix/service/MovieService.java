package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Movies;

public interface MovieService {

	public List<Movies> findAll();

	public Movies findById(String id);

	public List<Movies> findByFilter(String type, int year, String genre);

	public List<Movies> findTopRated(String type);

	public Movies create(Movies movie);

	public Movies update(String id, Movies movie);

	public void delete(String id);

}
