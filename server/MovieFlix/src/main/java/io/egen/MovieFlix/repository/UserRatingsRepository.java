package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserRatings;
import io.egen.MovieFlix.entity.Users;

//import io.egen.MovieFlix.entity.Languages;

public interface UserRatingsRepository {
	public List<UserRatings> findAll();

	public UserRatings findById(String id);

	public UserRatings findRating(Users user, Movies movie);

	public List<String> findStats(Movies movie);

	public UserRatings create(UserRatings uratings);

	public UserRatings update(UserRatings uratings);

	public void delete(UserRatings uratings);

}
