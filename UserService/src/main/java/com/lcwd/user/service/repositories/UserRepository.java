package com.lcwd.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
