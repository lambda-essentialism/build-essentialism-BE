package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "uservalues")
public class UserValues
  extends Auditable implements Serializable {
  @Id
  @JoinColumn(name = "userid")
  @JsonIgnoreProperties({ "userRoles", "hibernateLazyInitializer" })
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @Id
  @JoinColumn(name = "valueid")
  @JsonIgnoreProperties({ "userValues", "hibernateLazyInitializer" })
  @ManyToOne(fetch = FetchType.LAZY)
  private Value value;

  public UserValues() {}

  public UserValues(User user, Value value) {
    this.user = user;
    this.value = value;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Value getValue() {
    return value;
  }

  public void setValue(Value value) {
    this.value = value;
  }

}

