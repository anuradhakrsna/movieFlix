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
@NamedQueries({ @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Countries c ORDER BY c.country ASC"),
		@NamedQuery(name = "Countries.findByCountry", query = "SELECT c FROM Countries c WHERE c.country = :pcountry") })
public class Countries {

	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;

	@Column(unique = true, nullable = false)
	private String country;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
