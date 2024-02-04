package com.remy.jirastream.controller;

import com.remy.jirastream.entity.UserEntity;
import com.remy.jirastream.service.UserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;

  // Constructor injection of the UserService
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    UserEntity savedUser = userService.saveUser(user);

    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers() {
    List<UserEntity> users = userService.findAllUsers();
    logger.info("getting all users => findAllUsers : " + users);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
    return userService.findUserById(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<UserEntity>> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
    return userService.updateUser(id, user)
        .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}