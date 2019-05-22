package com.lambda.essentialism.repository;

import com.lambda.essentialism.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository
  extends CrudRepository<User, Long> {

  User findByUsername(String username);

}

