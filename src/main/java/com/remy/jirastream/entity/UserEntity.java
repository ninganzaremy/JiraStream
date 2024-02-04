package com.remy.jirastream.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Primary key

  private String username; // Username of the user
  private String password; // Password of the user, should be stored encrypted
  private String email; // Email of the user

  // Defines a many-to-many relationship with RoleEntity
  @ManyToMany(fetch = FetchType.EAGER) // EAGER fetching to load roles with the user
  @JoinTable(
      name = "user_roles", // Name of the join table
      joinColumns = @JoinColumn(name = "user_id"), // Column for the user_id
      inverseJoinColumns = @JoinColumn(name = "role_id") // Column for the role_id
  )
  private Set<RoleEntity> roles = new HashSet<>(); // Set of roles associated with the user

  // Default constructor
  public UserEntity() {}

  // Constructor with fields
  public UserEntity(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
  }

  // Adds a single role to the user
  public void addRole(RoleEntity role) {
    this.roles.add(role);
  }

  // Removes a single role from the user
  public void removeRole(RoleEntity role) {
    this.roles.remove(role);
  }
}