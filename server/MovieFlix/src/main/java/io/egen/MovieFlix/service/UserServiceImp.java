package io.egen.MovieFlix.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.MovieFlix.entity.Users;
import io.egen.MovieFlix.exception.AlreadyExistsException;
import io.egen.MovieFlix.exception.NotFoundException;
import io.egen.MovieFlix.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public List<Users> findAll() {
		return repository.findAll();
	}

	@Override
	public Users findbyId(String id) {
		Users existing = repository.findById(id);
		if (null == existing) {
			throw new NotFoundException("User with id= " + id + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Users create(Users user) {
		Users existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			throw new AlreadyExistsException(
					"the following User already exists with the email id = " + user.getEmail());
		}
		
		Users existingUser = repository.findByUserName(user.getUsername());
		if (existingUser != null) {
			throw new AlreadyExistsException(
					"the following User already exists with the username = " + user.getUsername());
		}

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			user.setPassword(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return repository.create(user);
	}

	@Override
	@Transactional
	public Users update(String id, Users user) {
		Users existing = repository.findById(id);
		if (existing == null) {
			throw new NotFoundException("User with id= " + id + " not found");
		}
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			user.setPassword(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Users existing = repository.findById(id);
		if (existing == null) {
			throw new NotFoundException("User with id= " + id + " not found");
		}

		repository.delete(existing);
	}
	
	@Override
	@Transactional
	public Users login(Users user) {
		
		Users existing = repository.findByEmail(user.getEmail());
		if (existing == null) {
			throw new AlreadyExistsException(
					"No user with email = " + user.getEmail());
		}
		
		if(user.getPassword() == null)
			return null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xFF & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			if(existing.getPassword().equals(hexString.toString())) {
				return existing;
			}
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
