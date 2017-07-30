package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Users;

public interface UserService {
	public List<Users> findAll();

	public Users findbyId(String id);

	public Users create(Users user);

	public Users update(String id, Users user);

	public void delete(String id);

}
