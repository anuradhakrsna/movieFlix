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
		@NamedQuery(name = "UserRatings.findAll", query = "SELECT ur FROM UserRatings ur ORDER BY ur.uratings ASC"),
		@NamedQuery(name = "UserRatings.findUratingsByMovie", query = "SELECT ur FROM UserRatings ur WHERE ur.users = :pUsers AND ur.movies=:pMovies"), })
public class UserRatings {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;

	@Column(nullable = false)
	private int uratings;

	@ManyToOne
	Users users;

	@ManyToOne
	Movies movies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUratings() {
		return uratings;
	}

	public void setUratings(int uratings) {
		this.uratings = uratings;
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

}
