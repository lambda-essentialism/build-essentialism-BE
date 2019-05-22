package com.lambda.essentialism.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambda.essentialism.model.User;
import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.repository.ValueRepository;
import com.lambda.essentialism.service.UserService;
import com.lambda.essentialism.service.ValueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ValueController {

  @Autowired
  private ValueService valueService;

  @Autowired
  private UserService userService;

  @JsonIgnoreProperties("userValues")
  @GetMapping("/values")
  public ResponseEntity<?> listAllValues() {
    List<Value> allValues = valueService.findAll();
    return new ResponseEntity<>(allValues, HttpStatus.OK);
  }

  @PostMapping("/values/{valueId}")
  public ResponseEntity<?> getValue(@PathVariable Long valueId) {

    String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

    long userid = userService.findUserByUsername(username).getUserid();

    valueService.saveUserValues(valueId, userid);

    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

}

