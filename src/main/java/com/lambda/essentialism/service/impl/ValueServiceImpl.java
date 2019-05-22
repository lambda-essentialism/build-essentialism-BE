package com.lambda.essentialism.service.impl;

import com.lambda.essentialism.exception.ResourceNotFoundException;
import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.repository.ValueRepository;

import java.util.ArrayList;
import java.util.List;

import com.lambda.essentialism.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "valueService")
@Transactional
public class ValueServiceImpl implements ValueService
{
  @Autowired
  private ValueRepository valueRepository;

  @Override
  public List<Value> findAll() {
    List<Value> list = new ArrayList<>();
    valueRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public Value save(Value message) {
    return null;
  }

  @Override
  public Value findById(long id) throws ResourceNotFoundException {
    return valueRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException((Long.toString(id))));
  }

  @Override
  public Value read(long id) {
    return null;
  }

  @Transactional
  public void saveUserValues(long userid, long roleid) {
    valueRepository.insertUserValues(userid, roleid);
  }

}

