package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Types;

public interface TypeRepository {

	public List<Types> findAll();

	public Types findById(String Id);

	public Types findByType(String type);

	public Types create(Types type);

	public Types update(Types type);

	public void delete(Types type);
}
