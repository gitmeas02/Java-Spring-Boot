package com.demo.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.login.entity.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity,Long>{
    
}
