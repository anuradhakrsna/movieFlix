package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserComments;
import io.egen.MovieFlix.entity.Users;

public interface UserCommentRepository {

	public List<UserComments> findAll();

	public UserComments findById(String id);

	public List<UserComments> findAllByMovie(Movies movies);

	public List<UserComments> findAllByUser(Users users);

	public UserComments create(UserComments comments);

	public void delete(UserComments comments);

}
