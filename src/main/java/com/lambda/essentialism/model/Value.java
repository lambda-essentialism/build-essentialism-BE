package com.lambda.essentialism.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long valueid;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @JsonIgnoreProperties("value")
  @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
  private List<UserValues> values = new ArrayList<>();

  public Value() {}

  public Value(String title) {
    this.title = title;
  }

  public long getValueid() {
    return valueid;
  }

  public void setValueid(long valueid) {
    this.valueid = valueid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<UserValues> getUserValues() {
    return values;
  }

  public void setUserValues(List<UserValues> userValues) {
    this.values = userValues;
  }
}

