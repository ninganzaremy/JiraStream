package com.remy.jirastream.repository;

import com.remy.jirastream.entity.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  // Find a role by its name
  Optional<RoleEntity> findByName(String name);
}