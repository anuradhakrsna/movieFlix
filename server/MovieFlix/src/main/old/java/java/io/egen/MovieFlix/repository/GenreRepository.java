package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Genre;

public interface GenreRepository {

	public List<Genre> findAll();
	
	public Genre findById(String Id);
	
	public Genre findByGenre(String genre);
	
	public Genre create(Genre genre);
	
	public Genre update(Genre genre);
	
	public void delete(Genre genre);

}
