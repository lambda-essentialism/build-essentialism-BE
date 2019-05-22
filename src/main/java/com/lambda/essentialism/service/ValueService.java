package com.lambda.essentialism.service;

import com.lambda.essentialism.model.User;
import com.lambda.essentialism.model.Value;

import java.util.List;

public interface ValueService {

  List<Value> findAll();

  Value save(Value message);

  Value findById(long id);

  Value read(long id);

  void saveUserValues(long valueid, long userid);
}

