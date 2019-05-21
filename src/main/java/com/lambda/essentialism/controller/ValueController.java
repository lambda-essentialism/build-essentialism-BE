package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.service.ValueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueController {
  @Autowired
  private ValueService valueService;

  @GetMapping(value = "/")
  @RequestMapping("/values")
  public ResponseEntity<?> listAllValues() {
    List<Value> allValues = valueService.findAll();
    return new ResponseEntity<>(allValues, HttpStatus.OK);
  }

}

