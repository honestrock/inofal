package com.mert.repository.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.mert.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
