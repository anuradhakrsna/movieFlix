package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Directors;

public interface DirectorService {

	public List<Directors> findAll();

	public Directors findById(String id);

	public Directors create(Directors director);

	public Directors update(String id, Directors director);

	public void delete(String id);

}
