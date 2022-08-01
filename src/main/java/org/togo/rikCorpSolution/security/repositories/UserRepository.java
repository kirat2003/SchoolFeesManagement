package org.togo.rikCorpSolution.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.togo.rikCorpSolution.security.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}