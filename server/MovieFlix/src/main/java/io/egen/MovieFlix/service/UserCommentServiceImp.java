package io.egen.MovieFlix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Movies;
import io.egen.MovieFlix.entity.UserComments;
import io.egen.MovieFlix.entity.Users;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.MovieRepository;
import io.egen.MovieFlix.repository.UserCommentRepository;
import io.egen.MovieFlix.repository.UserRepository;

@Service
public class UserCommentServiceImp implements UserCommentService {
	
	@Autowired
	UserCommentRepository repository;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	MovieRepository movierepository;

	@Override
	public List<UserComments> findAll() {
		return repository.findAll();
	}

	@Override
	public UserComments findById(String id) {
		UserComments existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("UserRatings with id : " + id + " not found");
		}

		return existing;
	}

	@Override
	public List<UserComments> findAllByMovie(String movieId) {
		
		Movies existingMovies= movierepository.findById(movieId);
		
		if( existingMovies == null) {
			throw new NotFoundException("Movie with id : " +movieId + " not found");
		}
		
		return repository.findAllByMovie(existingMovies);
		
	}

	@Override
	public List<UserComments> findAllByUser(String userId) {
		Users existingUsers= userrepository.findById(userId);
		
		if( existingUsers == null) {
			throw new NotFoundException("User with id : " +userId+ " not found");
		}
		
		return repository.findAllByUser(existingUsers);
	}

	@Override
	@Transactional
	public UserComments create(UserComments comments) {
		
		Users existingUser = userrepository.findById(comments.getUsers().getId());
		if(existingUser == null) {
			
			throw new NotFoundException("User with id : " + comments.getUsers().getId() + " not found");
		}
		comments.setUsers(existingUser);
		
		Movies existingMovies= movierepository.findById(comments.getMovies().getId());
		
		if( existingMovies == null) {

			throw new NotFoundException("Movie with id : " +comments.getMovies().getId() + " not found");
		}
		comments.setMovies(existingMovies);
		
	
		return repository.create(comments);
	}

	

	@Override
	@Transactional
	public void delete(String id) {
		UserComments existing = repository.findById(id);

		if (null == existing) {
			throw new NotFoundException("UserComments with id " + id + " not found");
		}
		repository.delete(existing);

	}

}
