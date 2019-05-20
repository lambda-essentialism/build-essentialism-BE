package com.lambda.essentialism.model;

import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  public Value() {}

  public Value(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}

