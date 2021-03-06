package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// User is considered the parent entity of all - the Grand Poobah!
@Entity
@Table(name = "users")
@JsonIgnoreProperties("authority")
public class User
  extends Auditable {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long userid;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @JsonIgnore
  @JsonIgnoreProperties({"user", "userRoles", "role"})
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserRoles> userRoles = new ArrayList<>();

  @JsonIgnoreProperties("user")
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserValues> userValues = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties("user")
  private List<Project> projects = new ArrayList<>();

  public User() {}

  public User(String firstname, String lastname, String email, String username, String password, List<UserRoles> userRoles) {
    setFirstname(firstname);
    setLastname(lastname);
    setEmail(email);
    setUsername(username);
    setPassword(password);
    for (UserRoles ur : userRoles) { ur.setUser(this); }
    this.userRoles = userRoles;
  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

  public String getFirstname() { return firstname; }

  public void setFirstname(String firstname) { this.firstname = firstname; }

  public String getLastname() { return lastname; }

  public void setLastname(String lastname) { this.lastname = lastname; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(password);
  }

  public void setPasswordNoEncrypt(String password) {
    this.password = password;
  }

  public List<UserRoles> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRoles> userRoles) {
    this.userRoles = userRoles;
  }

  public List<SimpleGrantedAuthority> getAuthority() {
    List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
    for (UserRoles r : this.userRoles) {
      String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
      rtnList.add(new SimpleGrantedAuthority(myRole));
    }
    return rtnList;
  }

  public List<UserValues> getUserValues() {
    return userValues;
  }

  public void setUserValues(List<UserValues> userValues) {
    this.userValues = userValues;
  }

  public List<Project> getProjects() { return projects; }

  public void setProjects(List<Project> projects) { this.projects = projects; }

}

