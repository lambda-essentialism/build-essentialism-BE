package com.lambda.essentialism.repo;

import com.lambda.essentialism.model.Value;

import org.springframework.data.repository.CrudRepository;

public interface ValueRepo
  extends CrudRepository<Value, Long> {}

