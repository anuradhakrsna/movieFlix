package io.egen.MovieFlix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Directors.findAll", query = "SELECT d FROM Directors d ORDER BY d.director ASC"),
		@NamedQuery(name = "Directors.findByDirector", query = "SELECT d FROM Directors d WHERE d.director = :pdirector") })
public class Directors {

	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;

	@Column(unique = true, nullable = false)
	private String director;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}
