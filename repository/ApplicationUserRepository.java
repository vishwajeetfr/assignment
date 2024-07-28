package com.leucine.assignment.repository;

import com.leucine.assignment.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
}
