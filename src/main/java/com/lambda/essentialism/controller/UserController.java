package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.User;
import com.lambda.essentialism.service.RoleService;
import com.lambda.essentialism.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api")
@RestController
public class UserController
{
  @Autowired
  private UserService userService;

  @Autowired RoleService roleService;

  // REGISTER NEW USER
  @PostMapping(value = "/register")
  public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
  {
    newuser =  userService.save(newuser);

    // set the location header for the newly created resource
    HttpHeaders responseHeaders = new HttpHeaders();
    URI newUserURI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(newuser.getUserid())
            .toUri();
    responseHeaders.setLocation(newUserURI);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @GetMapping(value = "/user/{userId}")
  public ResponseEntity<?> getUser(@PathVariable Long userId) {
    User u = userService.findUserById(userId);
    return new ResponseEntity<>(u, HttpStatus.OK);
  }

  @GetMapping(value = "/user")
  @ResponseBody
  public ResponseEntity<?> getCurrentUser(Authentication authentication) {
    return new ResponseEntity<>(userService.findUserByUsername(authentication.getName()), HttpStatus.OK);
  }
}

