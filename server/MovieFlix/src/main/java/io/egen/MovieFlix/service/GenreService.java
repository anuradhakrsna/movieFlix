package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Genres;

public interface GenreService {

	public List<Genres> findAll();

	public Genres findById(String id);

	public Genres create(Genres genre);

	public Genres update(String id, Genres genre);

	public void delete(String id);

}
