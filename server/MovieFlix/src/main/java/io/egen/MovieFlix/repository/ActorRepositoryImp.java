package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Actors;

@Repository
public class ActorRepositoryImp implements ActorRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Actors> findAll() {
		TypedQuery<Actors> query = em.createNamedQuery("Actors.findAll", Actors.class);
		List<Actors> actors = query.getResultList();
		return actors;
	}

	@Override
	public Actors findById(String Id) {
		return em.find(Actors.class, Id);
	}

	@Override
	public Actors findByActor(String actor) {
		TypedQuery<Actors> query = em.createNamedQuery("Actors.findByActor", Actors.class);
		query.setParameter("pactor", actor);
		List<Actors> actors = query.getResultList();

		if (actors != null && actors.size() == 1) {
			return actors.get(0);
		}

		return null;
	}

	@Override
	public Actors create(Actors actor) {
		em.persist(actor);
		return actor;
	}

	@Override
	public Actors update(Actors actor) {
		return em.merge(actor);
	}

	@Override
	public void delete(Actors actor) {
		em.remove(actor);
	}
}
