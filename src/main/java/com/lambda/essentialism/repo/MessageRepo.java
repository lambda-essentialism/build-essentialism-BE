package com.lambda.essentialism.repo;

import com.lambda.essentialism.model.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepo
  extends CrudRepository<Message, Long> {}

