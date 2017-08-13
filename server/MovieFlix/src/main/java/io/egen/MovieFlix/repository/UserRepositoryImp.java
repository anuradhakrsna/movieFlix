package io.egen.MovieFlix.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.MovieFlix.entity.Users;

@Repository
public class UserRepositoryImp implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Users> findAll() {
		TypedQuery<Users> query = em.createNamedQuery("User.findAll", Users.class);
		return query.getResultList();
	}

	@Override
	public Users findById(String id) {
		return em.find(Users.class, id);
	}

	@Override
	public Users findByEmail(String email) {
		TypedQuery<Users> query = em.createNamedQuery("User.finByEmail", Users.class);
		query.setParameter("pEmail", email);
		List<Users> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);

		}
		return null;
	}
	
	@Override
	public Users findByUserName(String username) {
		TypedQuery<Users> query = em.createNamedQuery("User.finByUsername", Users.class);
		query.setParameter("pUsername", username);
		List<Users> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);

		}
		return null;
	}

	@Override
	public Users create(Users user) {
		em.persist(user);
		return user;
	}

	@Override
	public Users update(Users user) {
		return em.merge(user);
	}

	@Override
	public void delete(Users user) {
		em.remove(user);

	}
	
	

}
