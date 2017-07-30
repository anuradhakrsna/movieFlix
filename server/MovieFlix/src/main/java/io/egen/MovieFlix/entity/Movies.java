package io.egen.MovieFlix.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Movie.findAll", query = "SELECT DISTINCT m FROM Movies m ORDER BY m.title ASC"),
		@NamedQuery(name = "Movie.findById", query = "SELECT DISTINCT m FROM Movies m WHERE m.id = :pId "),
		@NamedQuery(name = "Movie.topRatedShows", query = "SELECT DISTINCT m FROM Movies m JOIN m.imdb i "
				+ "join m.type t where t.type = :pType ORDER BY i.imdbRating DESC"),
		@NamedQuery(name = "Movie.sortByImdbRating", query = "SELECT DISTINCT m FROM Movies m "
				+ "JOIN m.imdb i ORDER BY i.imdbRating ASC "),
		@NamedQuery(name = "Movie.sortByTitle", query = "SELECT DISTINCT m FROM Movies m ORDER BY m.title "),
		@NamedQuery(name = "Movie.sortByYear", query = "SELECT DISTINCT m FROM Movies m ORDER BY m.year"),
		@NamedQuery(name = "Movie.sortByImdbVotes", query = "SELECT DISTINCT m FROM Movies m "
				+ "JOIN m.imdb i ORDER BY i.imdbVotes ")

})
public class Movies {

	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;

	String title;

	int year;

	@ManyToOne(optional = true)
	Ratings rated;

	String released;

	String runtime;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	Collection<Genres> genre;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	Collection<Directors> director;

	String writer;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	Collection<Actors> actors;

	String plot;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	Collection<Languages> language;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	Collection<Countries> country;

	String awards;

	String poster;

	int metaScore;

	@OneToOne
	Imdb imdb;

	@ManyToOne
	Types type;

	public Collection<Actors> getActors() {
		return actors;
	}

	public String getAwards() {
		return awards;
	}

	public Collection<Countries> getCountry() {
		return country;
	}

	public Collection<Directors> getDirector() {
		return director;
	}

	public Collection<Genres> getGenre() {
		return genre;
	}

	public String getId() {
		return id;
	}

	public Imdb getImdb() {
		return imdb;
	}

	public Collection<Languages> getLanguage() {
		return language;
	}

	public int getMetaScore() {
		return metaScore;
	}

	public String getPlot() {
		return plot;
	}

	public String getPoster() {
		return poster;
	}

	public Ratings getRated() {
		return rated;
	}

	public String getReleased() {
		return released;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getTitle() {
		return title;
	}

	public Types getType() {
		return type;
	}

	public String getWriter() {
		return writer;
	}

	public int getYear() {
		return year;
	}

	public void setActors(Collection<Actors> actors) {
		this.actors = actors;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public void setCountry(Collection<Countries> country) {
		this.country = country;
	}

	public void setDirector(Collection<Directors> director) {
		this.director = director;
	}

	public void setGenre(Collection<Genres> genre) {
		this.genre = genre;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImdb(Imdb imdb) {
		this.imdb = imdb;
	}

	public void setLanguage(Collection<Languages> language) {
		this.language = language;
	}

	public void setMetaScore(int metaScore) {
		this.metaScore = metaScore;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setRated(Ratings rated) {
		this.rated = rated;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
