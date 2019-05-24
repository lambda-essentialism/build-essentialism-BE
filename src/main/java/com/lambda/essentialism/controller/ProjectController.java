package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.Project;
import com.lambda.essentialism.model.User;
import com.lambda.essentialism.service.ProjectService;
import com.lambda.essentialism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProjectController
{
    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/projects", produces = {"application/json"})
    public ResponseEntity<?> findProjectsByUsername() {
        String userName = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<Project> theProjects = projectService.findByUserName(userName);
        return new ResponseEntity<>(theProjects, HttpStatus.OK);
    }

    @GetMapping(value = "/projects/{projectId}", produces = {"application/json"})
    public ResponseEntity<?> getQuote(@PathVariable Long projectId) {
        Project q = projectService.findProjectById(projectId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }

    @PostMapping(value = "/projects")
    public ResponseEntity<?> addNewQuote(@Valid @RequestBody Project newProject) throws URISyntaxException {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);
        newProject.setUser(user);
        newProject = projectService.save(newProject);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{projectid}")
                .buildAndExpand(newProject.getProjectid())
                .toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteQuoteById(@PathVariable long id) {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Project project = projectService.findProjectById(id);

        if (username.equalsIgnoreCase(project.getUser().getUsername())) {
            projectService.delete(project.getProjectid());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}