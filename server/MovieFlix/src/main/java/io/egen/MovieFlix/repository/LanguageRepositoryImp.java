package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Languages;

@Repository
public class LanguageRepositoryImp implements LanguageRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Languages> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<Languages> query = em.createNamedQuery("Languages.findAll", Languages.class);
		List<Languages> language = query.getResultList();
		return language;
	}

	@Override
	public Languages findById(String id) {
		// TODO Auto-generated method stub
		return em.find(Languages.class, id);
	}

	@Override
	public Languages findByLanguage(String language) {
		// TODO Auto-generated method stub
		TypedQuery<Languages> query = em.createNamedQuery("Languages.findByLanguage", Languages.class);
		query.setParameter("pLanguage", language);
		List<Languages> languages = query.getResultList();

		if (languages != null && languages.size() == 1) {
			return languages.get(0);
		}

		return null;
	}

	@Override
	public Languages create(Languages language) {
		// TODO Auto-generated method stub
		em.persist(language);
		return language;
	}

	@Override
	public Languages update(Languages language) {
		// TODO Auto-generated method stub
		return em.merge(language);
	}

	@Override
	public void delete(Languages language) {
		// TODO Auto-generated method stub
		em.remove(language);
	}

}
