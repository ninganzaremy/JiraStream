package com.remy.jirastream.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "issues")
public class IssueEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String description;
  private String status;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private ProjectEntity project;

  @ManyToOne
  @JoinColumn(name = "assignee_id")
  private UserEntity assignee;

  // Constructors
  public IssueEntity() {}

  public IssueEntity(String title, String description, String status, ProjectEntity project, UserEntity assignee) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.project = project;
    this.assignee = assignee;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return status;
  }

  public ProjectEntity getProject() {
    return project;
  }

  public UserEntity getAssignee() {
    return assignee;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setProject(ProjectEntity project) {
    this.project = project;
  }

  public void setAssignee(UserEntity assignee) {
    this.assignee = assignee;
  }
}