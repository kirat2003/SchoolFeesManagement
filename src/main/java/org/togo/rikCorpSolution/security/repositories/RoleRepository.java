package org.togo.rikCorpSolution.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.togo.rikCorpSolution.security.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}