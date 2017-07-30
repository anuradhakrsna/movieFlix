package io.egen.MovieFlix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.MovieFlix.entity.UserComments;
import io.egen.MovieFlix.service.UserCommentService;

@RestController
@RequestMapping(path = "comments")
public class UserCommentController {
	@Autowired
	UserCommentService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserComments> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserComments findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "movie/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserComments> findAllByMovie(@PathVariable("id") String id) {
		return service.findAllByMovie(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserComments> findAllByUser(@PathVariable("id") String id) {
		return service.findAllByUser(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserComments create(@RequestBody UserComments comments) {
		return service.create(comments);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

}
