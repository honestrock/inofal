package com.mert.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mert.model.Role;
import com.mert.model.User;

public class BaseMongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Drop Existing Collections
	 * 
	 * Creating First Records
	 * 
	 * Inserting to db
	 * 
	 * @author Mert Kaya
	 * 
	 */
	public void init() {
		mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("user");

		Role adminRole = new Role();
		adminRole.setId(UUID.randomUUID().toString());
		adminRole.setRole(1);

		Role userRole = new Role();
		userRole.setId(UUID.randomUUID().toString());
		userRole.setRole(2);

		User mert = new User();
		mert.setId(UUID.randomUUID().toString());
		mert.setFirstName("Mert");
		mert.setLastName("Kaya");
		mert.setPassword("21232f297a57a5a743894a0e4a801fc3");
		mert.setRole(adminRole);
		mert.setUsername("Mert");

		User gul = new User();
		gul.setId(UUID.randomUUID().toString());
		gul.setFirstName("Gülşen");
		gul.setLastName("Kaya");
		gul.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		gul.setRole(userRole);
		gul.setUsername("Gülşen");

		mongoTemplate.insert(mert, "user");
		mongoTemplate.insert(gul, "user");
		mongoTemplate.insert(adminRole, "role");
		mongoTemplate.insert(userRole, "role");
	}
}
