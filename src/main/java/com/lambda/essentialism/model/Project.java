package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@JsonIgnoreProperties("user")
public class Project extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectid;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"projects", "hibernateLazyInitializer"})
    private User user;

    public Project() {}

    public Project(String title, User user)
    {
        this.title = title;
        this.user = user;
    }

    public long getProjectid() { return projectid; }

    public void setProjectid(long projectid) { this.projectid = projectid; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}