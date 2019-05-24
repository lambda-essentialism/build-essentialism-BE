package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.User;
import com.lambda.essentialism.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class AdminController
{
  @Autowired
  private UserService userService;

  @CrossOrigin(origins = "*")
  @GetMapping(value = "/users", produces = { "application/json" })
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<?> listAllUsers() {
    List<User> myUsers = userService.findAll();
    return new ResponseEntity<>(myUsers, HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @GetMapping(value = "/getusername", produces = { "application/json" })
  @ResponseBody
  public ResponseEntity<?> getCurrentUserName(Authentication authentication) {
    return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @PutMapping(value = "/user/{id}")
  public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id) {
    userService.update(updateUser, id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @CrossOrigin(origins = "*")
  @DeleteMapping("/user/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<?> deleteUserById(@PathVariable long id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}

