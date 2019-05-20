package com.lambda.essentialism.service;

import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.repo.ValueRepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "valueService")
@Transactional
public class ValueServiceImpl implements ValueService {
  @Autowired
  private ValueRepo valueRepo;

  @Override
  public List<Value> findAll() {
    List<Value> list = new ArrayList<>();
    valueRepo.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public Value save(Value message) {
    return null;
  }

  @Override
  public Value findMsgById(long id) {
    return null;
  }

  @Override
  public Value read(long id) {
    return null;
  }

}

