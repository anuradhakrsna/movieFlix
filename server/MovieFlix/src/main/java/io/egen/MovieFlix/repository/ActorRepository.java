package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Actors;

public interface ActorRepository {

	public List<Actors> findAll();

	public Actors findById(String Id);

	public Actors findByActor(String actor);

	public Actors create(Actors actor);

	public Actors update(Actors actor);

	public void delete(Actors actor);

}
