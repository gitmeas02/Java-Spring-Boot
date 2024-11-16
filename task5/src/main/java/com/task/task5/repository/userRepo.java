package com.task.task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.task.task5.model.EntityUser;

public interface userRepo extends JpaRepository<EntityUser, Long> {}
