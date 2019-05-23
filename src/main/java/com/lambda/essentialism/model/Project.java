package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectid;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"project", "projects", "userProjects", "user"})
    private List<UserProjects> projects = new ArrayList<>();

    public Project() {
    }

    public Project(String title, List<UserProjects> projects) {
        this.title = title;
        this.projects = projects;
    }

    public long getProjectid() {
        return projectid;
    }

    public void setProjectid(long projectid) {
        this.projectid = projectid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserProjects> getProjects() {
        return projects;
    }

    public void setProjects(List<UserProjects> projects) {
        this.projects = projects;
    }
}
