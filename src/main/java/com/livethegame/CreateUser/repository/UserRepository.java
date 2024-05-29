package com.livethegame.CreateUser.repository;

import com.livethegame.CreateUser.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
