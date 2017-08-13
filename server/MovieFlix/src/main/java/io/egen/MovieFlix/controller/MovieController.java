package io.egen.MovieFlix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.service.MovieService;

@RestController
@RequestMapping(path = "movies")
public class MovieController {

	@Autowired
	MovieService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movies> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movies findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "filters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movies> findByFilter(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "year", required = false, defaultValue = "0") int year,
			@RequestParam(value = "genre", required = false) String genre) {
		return service.findByFilter(type, year, genre);
	}

	@RequestMapping(method = RequestMethod.GET, path = "toprated", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movies> findTopRated(
			@RequestParam(value = "type", required = false, defaultValue = "movie") String type) {
		return service.findTopRated(type);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movies> findLatest(){			
		return service.findLatest();
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movies create(@RequestBody Movies movie) {
		return service.create(movie);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movies update(@PathVariable("id") String id, @RequestBody Movies movie) {
		return service.update(id, movie);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}
