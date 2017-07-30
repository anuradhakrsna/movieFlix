package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Actors;

public interface ActorService {

	public List<Actors> findAll();

	public Actors findById(String id);

	public Actors create(Actors actor);

	public Actors update(String id, Actors actor);

	public void delete(String id);
}
