package io.egen.MovieFlix.repository;

import java.util.List;

import io.egen.MovieFlix.entity.Countries;

public interface CountryRepository {

	public List<Countries> findAll();

	public Countries findById(String Id);

	public Countries findByCountry(String country);

	public Countries create(Countries country);

	public Countries update(Countries country);

	public void delete(Countries country);

}
