package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role
  extends Auditable {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long roleid;

  @Column(nullable = false, unique = true)
  String name;

  @JsonIgnoreProperties("role")
  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
  private List<UserRoles> userRoles = new ArrayList<>();

  public Role() {}

  public Role(String name) {
    this.name = name;
  }

  public long getRoleid() {
    return roleid;
  }

  public void setRoleid(long roleid) {
    this.roleid = roleid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<UserRoles> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRoles> userRoles) {
    this.userRoles = userRoles;
  }

}

