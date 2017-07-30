package io.egen.MovieFlix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "UserComments.findAll", query = "SELECT uc FROM UserComments uc ORDER BY uc.time DESC"),
		@NamedQuery(name = "UserComments.findAllByMovie", query = "SELECT uc FROM UserComments uc WHERE uc.movies=:pMovies"),
		@NamedQuery(name = "UserComments.findAllByUsers", query = "SELECT uc FROM UserComments uc WHERE uc.users=:pUsers") })
public class UserComments {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;

	@ManyToOne
	Users users;

	@ManyToOne
	Movies movies;

	@Column(nullable = false)
	private String comments;

	@Column(nullable = false)
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
