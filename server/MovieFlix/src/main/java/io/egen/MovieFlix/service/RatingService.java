package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Ratings;

public interface RatingService {

	public List<Ratings> findAll();

	public Ratings findById(String id);

	public Ratings create(Ratings rating);

	public Ratings update(String id, Ratings rating);

	public void delete(String id);
}
