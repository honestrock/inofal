package com.mert.repository.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mert.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
