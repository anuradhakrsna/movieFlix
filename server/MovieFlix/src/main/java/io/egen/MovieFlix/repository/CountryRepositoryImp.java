package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Countries;

@Repository
public class CountryRepositoryImp implements CountryRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Countries> findAll() {
		TypedQuery<Countries> query = em.createNamedQuery("Countries.findAll", Countries.class);
		List<Countries> countries = query.getResultList();
		return countries;
	}

	@Override
	public Countries findById(String Id) {
		return em.find(Countries.class, Id);
	}

	@Override
	public Countries findByCountry(String country) {
		TypedQuery<Countries> query = em.createNamedQuery("Countries.findByCountry", Countries.class);
		query.setParameter("pcountry", country);
		List<Countries> countries = query.getResultList();

		if (countries != null && countries.size() == 1) {
			return countries.get(0);
		}

		return null;
	}

	@Override
	public Countries create(Countries country) {
		em.persist(country);
		return country;
	}

	@Override
	public Countries update(Countries country) {
		return em.merge(country);
	}

	@Override
	public void delete(Countries country) {
		em.remove(country);
	}

}
