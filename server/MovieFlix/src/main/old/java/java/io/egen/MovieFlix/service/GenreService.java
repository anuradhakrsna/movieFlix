package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Genre;

public interface GenreService {

	public List<Genre> findAll();

	public Genre findById(String id);

	public Genre create(Genre genre);

	public Genre update(String id, Genre genre);

	public void delete(String id);

}
