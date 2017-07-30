package io.egen.MovieFlix.controller;

import java.util.List;

import javax.json.Json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egen.MovieFlix.entity.UserRatings;
import io.egen.MovieFlix.service.UserRatingsService;

@RestController
@RequestMapping(path = "uratings")
public class UserRatingsController {

	@Autowired
	UserRatingsService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserRatings> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRatings findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "findRating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRatings findRating(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "movieId", required = true) String movieId) {
		return service.findRating(userId, movieId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "findStats", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findStats(@RequestParam(value = "movieId", required = true) String movieId) {
		List<String> vals = service.findStats(movieId);

		String jsonString = Json.createObjectBuilder().add("averageRating", vals.get(0)).add("NoOfRating", vals.get(1))
				.build().toString();

		return jsonString;
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRatings create(@RequestBody UserRatings uratings) {
		return service.create(uratings);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRatings update(@PathVariable("id") String id, @RequestBody UserRatings uratings) {
		return service.update(id, uratings);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

}
