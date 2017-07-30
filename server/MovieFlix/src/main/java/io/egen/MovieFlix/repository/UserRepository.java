package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Users;

public interface UserRepository {

	public List<Users> findAll();

	public Users findById(String id);

	public Users findByEmail(String email);

	public Users create(Users user);

	public Users update(Users user);

	public void delete(Users user);

}
