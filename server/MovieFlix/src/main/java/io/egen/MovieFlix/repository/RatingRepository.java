package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Ratings;

public interface RatingRepository {

	public List<Ratings> findAll();

	public Ratings findById(String id);

	public Ratings findByRating(String rating);

	public Ratings create(Ratings rating);

	public Ratings update(Ratings rating);

	public void delete(Ratings rating);
}
