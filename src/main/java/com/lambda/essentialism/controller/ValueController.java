package com.lambda.essentialism.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambda.essentialism.exception.ResourceNotFoundException;
import com.lambda.essentialism.model.Value;
import com.lambda.essentialism.service.UserService;
import com.lambda.essentialism.service.ValueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  // POST: Add new user value
  @PostMapping("/values/{valueId}")
  public ResponseEntity<?> addUserValue(@PathVariable Long valueId) {

    String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

    long userid = userService.findUserByUsername(username).getUserid();

    valueService.saveUserValue(valueId, userid);

    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }


  // DELETE: Add new user value
  @DeleteMapping("/values/{valueId}")
  public ResponseEntity<?> deleteUserValue(@PathVariable Long valueId) throws ResourceNotFoundException {

    String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

    long userid = userService.findUserByUsername(username).getUserid();

    Value value = valueService.findById(valueId);
    if (value != null) {
      valueService.deleteUserValue(valueId, userid);
      return new ResponseEntity<>(null, HttpStatus.OK);
    } else {
      return null;
    }
  }

}

