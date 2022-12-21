package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}
