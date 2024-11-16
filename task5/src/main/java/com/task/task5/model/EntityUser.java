package com.task.task5.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class EntityUser {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(length = 100)
   private String username;

   @Column(length = 100)
  private String email;

  @Column(length = 100)
  private String password;

  @Column(length = 255)
  private String description;
  
  @CreationTimestamp
  private Date createdAt;
  
  @CreationTimestamp
  private Date updatedAt;
  public EntityUser() {
}
public EntityUser(String username, String email, String password, String description) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.description = description;

}
public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Date getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
}

public Date getUpdatedAt() {
    return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
}

}
