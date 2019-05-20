package com.lambda.essentialism.service;

import com.lambda.essentialism.model.Value;

import java.util.List;

public interface ValueService {

  List<Value> findAll();

  Value save(Value message);

  Value findMsgById(long id);

  Value read(long id);

}

