package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.UserComments;

public interface UserCommentService {

	public List<UserComments> findAll();

	public UserComments findById(String id);

	public List<UserComments> findAllByMovie(String movieId);

	public List<UserComments> findAllByUser(String userId);

	public UserComments create(UserComments comments);

	public void delete(String id);

}
