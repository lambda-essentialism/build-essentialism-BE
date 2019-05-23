package com.lambda.essentialism.service.impl;

import com.lambda.essentialism.model.Project;
import com.lambda.essentialism.repository.ProjectRepository;
import com.lambda.essentialism.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public List<Project> findUserProjects(long userid) {
        List<Project> list = new ArrayList<>();
        projectRepository.findUserProjects(userid).iterator().forEachRemaining(list::add);
        return list;
    }
}
