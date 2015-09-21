package com.mert.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.User;
import com.mert.repository.mongo.RoleRepository;
import com.mert.repository.mongo.UserRepository;

@Service
public class UserService {
	


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * <h1>Createing User Model With Random uniq ID</h1>
	 * Save it to repository
	 * @author Mert Kaya
	 * @param user Domain Model of user
	 * @return
	 */
	public User create(User user) {
		user.setId(UUID.randomUUID().toString());
		user.getRole().setId(UUID.randomUUID().toString());
		
		roleRepository.save(user.getRole());
		return userRepository.save(user);
	}
	
	/**
	 * 
	 * @author Mert Kaya
	 * @param user
	 * @return
	 */
	public User read(User user) {
		return user;
	}
	
	/**
	 * @author Mert Kaya
	 * @return
	 */
	public List<User> readAll() {
		return userRepository.findAll();
	}
	
	/**
	 * @author Mert Kaya
	 * @param user
	 * @return
	 */
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		
		roleRepository.save(existingUser.getRole());
		return userRepository.save(existingUser);
	}
	
	/**
	 * Normaly dont delete from db change status colomn
	 * 
	 * @author Mert Kaya
	 * @param user
	 * @return
	 */
	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return false;
		}
		
		roleRepository.delete(existingUser.getRole());
		userRepository.delete(existingUser);
		return true;
	}
}
