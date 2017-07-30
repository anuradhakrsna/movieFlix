package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Types;

public interface TypeService {

	public List<Types> findAll();

	public Types findById(String id);

	public Types create(Types type);

	public Types update(String id, Types type);

	public void delete(String id);

}
