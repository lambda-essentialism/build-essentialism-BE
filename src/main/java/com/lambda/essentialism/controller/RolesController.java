package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.Role;
import com.lambda.essentialism.service.RoleService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*")
@RequestMapping("/roles")
@RestController
public class RolesController {
  @Autowired
  RoleService roleService;

  @GetMapping(value = "/roles", produces = { "application/json" })
  @CrossOrigin(origins = "*")
  public ResponseEntity<?> listRoles() {
    List<Role> allRoles = roleService.findAll();
    return new ResponseEntity<>(allRoles, HttpStatus.OK);
  }

  @GetMapping(value = "/role/{roleId}", produces = { "application/json" })
  @CrossOrigin(origins = "*")
  public ResponseEntity<?> getRole(@PathVariable Long roleId) {
    Role r = roleService.findRoleById(roleId);
    return new ResponseEntity<>(r, HttpStatus.OK);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(value = "/role")
  public ResponseEntity<?> addNewRole(@RequestBody @Valid Role newRole) throws URISyntaxException {
    newRole = roleService.save(newRole);

    // set the location header for the newly created resource
    HttpHeaders responseHeaders = new HttpHeaders();
    URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{roleid}").buildAndExpand(newRole.getRoleid()).toUri();
    responseHeaders.setLocation(newRoleURI);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @CrossOrigin(origins = "*")
  @PutMapping(value = "/role/{roleid}")
  public ResponseEntity<?> updateRole(@RequestBody Role updateRole, @PathVariable long roleid) {
    roleService.update(updateRole, roleid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/role/{id}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<?> deleteRoleById(@PathVariable long id) {
    roleService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/user/{userid}/role/{roleid}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<?> addUserRole(@PathVariable long userid, @PathVariable long roleid) {
    roleService.saveUserRole(userid, roleid);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(value = "/user/{userid}/role/{roleid}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<?> deleteUserRole(@PathVariable long userid, @PathVariable long roleid) {
    roleService.deleteUserRole(userid, roleid);

    return new ResponseEntity<>(HttpStatus.OK);
  }

}

