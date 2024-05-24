package com.livethegame.CreateUser.repository;

import com.livethegame.CreateUser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
