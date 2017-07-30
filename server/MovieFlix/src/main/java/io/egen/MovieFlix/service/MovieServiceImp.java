package io.egen.MovieFlix.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Actors;
import io.egen.MovieFlix.entity.Countries;
import io.egen.MovieFlix.entity.Directors;
import io.egen.MovieFlix.entity.Genres;
import io.egen.MovieFlix.entity.Languages;
import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.Ratings;
import io.egen.MovieFlix.entity.Types;
import io.egen.MovieFlix.exception.MismatchException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.ActorRepository;
import io.egen.MovieFlix.repository.CountryRepository;
import io.egen.MovieFlix.repository.DirectorRepository;
import io.egen.MovieFlix.repository.GenreRepository;
import io.egen.MovieFlix.repository.ImdbRepository;
import io.egen.MovieFlix.repository.LanguageRepository;
import io.egen.MovieFlix.repository.MovieRepository;
import io.egen.MovieFlix.repository.RatingRepository;
import io.egen.MovieFlix.repository.TypeRepository;

@Service
public class MovieServiceImp implements MovieService {

	@Autowired
	MovieRepository repository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	DirectorRepository directorRepository;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	ImdbRepository imdbRepository;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Override
	public List<Movies> findAll() {
		return repository.findAll();
	}

	@Override
	public Movies findById(String id) {
		Movies existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("Movie with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	public List<Movies> findByFilter(String type, int year, String genre) {
		return repository.findByFilter(type, year, genre);
	}

	@Override
	@Transactional
	public Movies create(Movies movie) {

		if (movie.getRated() != null) {
			Ratings foundRating = ratingRepository.findByRating(movie.getRated().getRating());
			if (null == foundRating) {
				movie.setRated(ratingRepository.create(movie.getRated()));
			} else {
				movie.setRated(foundRating);
			}
		}

		List<Genres> finalGenreList = new ArrayList<Genres>();
		for (Genres genre : movie.getGenre()) {

			Genres foundGenre = genreRepository.findByGenre(genre.getGenre());
			if (null == foundGenre) {
				finalGenreList.add(genreRepository.create(genre));
			} else {
				finalGenreList.add(foundGenre);
			}
		}
		movie.setGenre(finalGenreList);

		List<Directors> finalDirectorList = new ArrayList<Directors>();
		for (Directors director : movie.getDirector()) {

			Directors foundDirector = directorRepository.findByDirector(director.getDirector());
			if (null == foundDirector) {
				finalDirectorList.add(directorRepository.create(director));
			} else {
				finalDirectorList.add(foundDirector);
			}
		}
		movie.setDirector(finalDirectorList);

		List<Actors> finalActorList = new ArrayList<Actors>();
		for (Actors actor : movie.getActors()) {

			Actors foundActor = actorRepository.findByActor(actor.getActor());
			if (null == foundActor) {
				finalActorList.add(actorRepository.create(actor));
			} else {
				finalActorList.add(foundActor);
			}
		}
		movie.setActors(finalActorList);

		List<Languages> finalLanguageList = new ArrayList<Languages>();
		for (Languages language : movie.getLanguage()) {

			Languages foundLanguage = languageRepository.findByLanguage(language.getLanguage());
			if (null == foundLanguage) {
				finalLanguageList.add(languageRepository.create(language));
			} else {
				finalLanguageList.add(foundLanguage);
			}
		}
		movie.setLanguage(finalLanguageList);

		List<Countries> finalCountryList = new ArrayList<Countries>();
		for (Countries country : movie.getCountry()) {

			Countries foundCountry = countryRepository.findByCountry(country.getCountry());
			if (null == foundCountry) {
				finalCountryList.add(countryRepository.create(country));
			} else {
				finalCountryList.add(foundCountry);
			}
		}
		movie.setCountry(finalCountryList);

		movie.setImdb(imdbRepository.create(movie.getImdb()));

		Types foundType = typeRepository.findByType(movie.getType().getType());
		if (null == foundType) {
			movie.setType(typeRepository.create(movie.getType()));
		} else {
			movie.setType(foundType);
		}

		return repository.create(movie);
	}

	@Override
	@Transactional
	public Movies update(String id, Movies movie) {

		Movies existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Movie with id " + id + " not found");
		}

		if (!id.equals(movie.getId())) {
			throw new MismatchException("Request id " + id + " does not match the object id " + movie.getId());
		}

		return repository.update(movie);
	}

	@Override
	@Transactional
	public void delete(String id) {

		Movies existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("Movie with id " + id + " not found");
		}
		repository.delete(existing);
	}

	@Override
	public List<Movies> findTopRated(String type) {

		if (typeRepository.findByType(type) == null) {
			throw new NotFoundException("Type " + type + " not found");
		}

		return repository.findTopRated(type);
	}

}
