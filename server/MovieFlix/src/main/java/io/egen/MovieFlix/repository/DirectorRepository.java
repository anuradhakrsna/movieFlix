package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Directors;

public interface DirectorRepository {

	public List<Directors> findAll();

	public Directors findById(String Id);

	public Directors findByDirector(String director);

	public Directors create(Directors director);

	public Directors update(Directors director);

	public void delete(Directors director);

}
