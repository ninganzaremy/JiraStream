package com.remy.jirastream.service;


import com.remy.jirastream.entity.RoleEntity;
import com.remy.jirastream.entity.UserEntity;
import com.remy.jirastream.repository.RoleRepository;
import com.remy.jirastream.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserEntity> findAllUsers() {
    return userRepository.findAll();
  }

  public Optional<UserEntity> findUserById(Long id) {
    return userRepository.findById(id);
  }

  public UserEntity saveUser(UserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
    return userRepository.save(user);
  }

  public Optional<Optional<UserEntity>> updateUser(Long id, UserEntity userDetails) {
    return userRepository.findById(id).map(existingUser -> {
      // Update the non-null and altered fields only
      if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
        existingUser.setUsername(userDetails.getUsername());
      }
      if (userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()) {
        existingUser.setEmail(userDetails.getEmail());
      }
      // Check if password is changed and not empty before encoding and updating
      if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
      }
      // Save and return the updated user entity
      return Optional.of(userRepository.save(existingUser));
    });
  }

  public void deleteUserById(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }
    userRepository.deleteById(id);
  }

  public UserEntity addRoleToUser(Long userId, Long roleId) {
    UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    user.getRoles().add(role);
    return userRepository.save(user);
  }

  public UserEntity removeRoleFromUser(Long userId, Long roleId) {
    UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    user.getRoles().remove(role);
    return userRepository.save(user);
  }
}