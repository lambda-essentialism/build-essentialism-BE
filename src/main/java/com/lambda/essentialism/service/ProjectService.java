package com.lambda.essentialism.service;

import com.lambda.essentialism.model.Project;

import java.util.List;

public interface ProjectService
{
    List<Project> findAll();

    Project findProjectById(long id);

    List<Project> findByUserName(String username);

    void delete(long id);

    Project save(Project project);
}