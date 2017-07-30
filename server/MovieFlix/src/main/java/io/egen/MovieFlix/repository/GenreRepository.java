package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Genres;

public interface GenreRepository {

	public List<Genres> findAll();

	public Genres findById(String Id);

	public Genres findByGenre(String genre);

	public Genres create(Genres genre);

	public Genres update(Genres genre);

	public void delete(Genres genre);

}
