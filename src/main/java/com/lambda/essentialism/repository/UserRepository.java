package com.lambda.essentialism.repository;

import com.lambda.essentialism.model.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository
  extends CrudRepository<User, Long> {

  User findByUsername(String username);

//  @Modifying
//  @Query(value = "SELECT userid FROM users WHERE username=(:username)", nativeQuery = true)
//  @Transactional
//  User findUserIdByUsername(String username);

}

