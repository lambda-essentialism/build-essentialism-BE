package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long valueid;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @JsonIgnoreProperties("value")
  @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
  private List<UserValues> userRoles = new ArrayList<>();

  public Value() {}

  public Value(String title) {
    this.title = title;
  }

  public long getId() {
    return valueid;
  }

  public void setId(long id) {
    this.valueid = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<UserValues> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserValues> userRoles) {
    this.userRoles = userRoles;
  }

}

