package com.lambda.essentialism.service;

import com.lambda.essentialism.model.Subscriber;

import java.util.List;

public interface SubscriberService {

  List<Subscriber> findAll();

  Subscriber save(Subscriber subscriber);

  Subscriber findSubscriberById(long id);

  void delete(long id);

}

