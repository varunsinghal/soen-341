package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.User;

public interface UserRepository  extends CrudRepository<User, String>{

}
