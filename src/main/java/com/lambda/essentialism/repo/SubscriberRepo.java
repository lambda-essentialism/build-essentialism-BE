package com.lambda.essentialism.repo;

import com.lambda.essentialism.model.Subscriber;

import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepo
  extends CrudRepository<Subscriber, Long> {}

