package com.remy.jirastream.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class ProjectEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private UserEntity owner;

  // Constructors
  public ProjectEntity() {}

  public ProjectEntity(String name, String description, UserEntity owner) {
    this.name = name;
    this.description = description;
    this.owner = owner;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public UserEntity getOwner() {
    return owner;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setOwner(UserEntity owner) {
    this.owner = owner;
  }
}