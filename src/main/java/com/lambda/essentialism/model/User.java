package com.lambda.essentialism.model;

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
public class User
  extends Auditable {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long userid;

  @Column(nullable = false, unique = true)
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @JsonIgnoreProperties("user")
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<UserRoles> userRoles = new ArrayList<>();

  @JsonIgnoreProperties("value")
  @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
  private List<UserValues> userValues = new ArrayList<>();

  public User() {}

  public User(String username, String password, List<UserRoles> userRoles, List<UserValues> userValues) {
    setUsername(username);
    setPassword(password);
    for (UserRoles ur : userRoles) {
      ur.setUser(this);
    }
    this.userRoles = userRoles;
    this.userValues = userValues;

  }

  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

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
}

