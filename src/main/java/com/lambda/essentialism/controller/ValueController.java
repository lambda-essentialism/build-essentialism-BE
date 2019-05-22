package com.lambda.essentialism.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.repo.ValueRepo;
import com.lambda.essentialism.service.UserService;
import com.lambda.essentialism.service.ValueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ValueController {
  @Autowired
  private ValueService valueService;
  private UserService userService;
  private ValueRepo valueRepo;

  @GetMapping("/values")
//  @JsonIgnoreProperties("userValues")
  public ResponseEntity<?> listAllValues() {
    List<Value> allValues = valueService.findAll();
    return new ResponseEntity<>(allValues, HttpStatus.OK);
  }

  @GetMapping("/values/{valueId}")
  public ResponseEntity<?> getValue(@PathVariable Long valueId) {
    Value value = valueService.findById(valueId);
    return new ResponseEntity<>(value, HttpStatus.OK);
  }

}

