package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.UserRatings;

//import io.egen.MovieFlix.entity.Languages;

public interface UserRatingsService {
	public List<UserRatings> findAll();

	public UserRatings findById(String id);

	public UserRatings findRating(String userId, String movieId);

	public List<String> findStats(String movieId);

	public UserRatings create(UserRatings uratings);

	public UserRatings update(String id, UserRatings uratings);

	public void delete(String id);

}
