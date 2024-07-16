package com.example.springwithreactappln.springbootreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springwithreactappln.springbootreact.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
