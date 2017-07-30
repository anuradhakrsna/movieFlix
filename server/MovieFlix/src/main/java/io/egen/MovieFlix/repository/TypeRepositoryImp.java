package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Types;

@Repository
public class TypeRepositoryImp implements TypeRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Types> findAll() {
		TypedQuery<Types> query = em.createNamedQuery("Types.findAll", Types.class);
		List<Types> types = query.getResultList();
		return types;
	}

	@Override
	public Types findById(String Id) {
		return em.find(Types.class, Id);
	}

	@Override
	public Types findByType(String type) {
		TypedQuery<Types> query = em.createNamedQuery("Types.findByType", Types.class);
		query.setParameter("pType", type);
		List<Types> types = query.getResultList();

		if (types != null && types.size() == 1) {
			return types.get(0);
		}

		return null;
	}

	@Override
	public Types create(Types type) {
		em.persist(type);
		return type;
	}

	@Override
	public Types update(Types type) {
		return em.merge(type);
	}

	@Override
	public void delete(Types type) {
		em.remove(type);
	}

}
