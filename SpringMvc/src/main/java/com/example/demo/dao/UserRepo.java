package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
 Users findByUsername(String uname);
}
