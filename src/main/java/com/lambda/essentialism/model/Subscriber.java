package com.lambda.essentialism.model;

import javax.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;

  //  @Column(name = "email", nullable = false, unique = true)
  private String email;

  public Subscriber() {}

  public Subscriber(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}

