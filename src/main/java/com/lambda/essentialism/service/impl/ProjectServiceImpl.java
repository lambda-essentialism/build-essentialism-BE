package com.lambda.essentialism.service.impl;

import com.lambda.essentialism.model.Project;
import com.lambda.essentialism.repository.ProjectRepository;
import com.lambda.essentialism.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "quoteService")
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll()
    {
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Project findProjectById(long id)
    {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (projectRepository.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (projectRepository.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                projectRepository.deleteById(id);
            }
            else
            {
                throw new EntityNotFoundException(Long.toString(id) + " " + authentication.getName());
            }
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Project save(Project project)
    {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findByUserName(String username)
    {
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}