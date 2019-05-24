package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.Project;
import com.lambda.essentialism.service.ProjectService;
import com.lambda.essentialism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/allprojects")
    public ResponseEntity<?> listAllProjects() {
        List<Project> allProjects = projectService.findAll();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }


//    @GetMapping("/projects")
//    public ResponseEntity<?> listUserProjects() {
//
//        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//
//        long userid = userService.findUserByUsername(username).getUserid();
//
//        List<Project> userProjects = projectService.findUserProjects(userid);
//        return new ResponseEntity<>(userProjects, HttpStatus.OK);
//    }



}
