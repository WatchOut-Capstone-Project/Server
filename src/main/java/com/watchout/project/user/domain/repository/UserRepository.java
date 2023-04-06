package com.watchout.project.user.domain.repository;

import com.watchout.project.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
