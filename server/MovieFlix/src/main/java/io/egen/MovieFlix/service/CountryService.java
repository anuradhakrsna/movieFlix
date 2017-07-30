package io.egen.MovieFlix.service;

import java.util.List;

import io.egen.MovieFlix.entity.Countries;

public interface CountryService {

	public List<Countries> findAll();

	public Countries findById(String id);

	public Countries create(Countries country);

	public Countries update(String id, Countries country);

	public void delete(String id);

}
