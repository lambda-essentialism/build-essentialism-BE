package com.lambda.essentialism.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userprojects")
public class UserProjects
    extends Auditable implements Serializable {

    @Id
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({ "userRoles", "hibernateLazyInitializer" })
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Id
    @JoinColumn(name = "projectid")
    @JsonIgnoreProperties({ "userProjects", "hibernateLazyInitializer" })
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public UserProjects() {}

    public UserProjects(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Project getProject() { return project; }

    public void setProject(Project project) { this.project = project; }
}
