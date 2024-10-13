package com.sportyshoes.SportyShoesApp.entity;

import com.sportyshoes.SportyShoesApp.type.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String email;
    private String password;
    private String userType;
    private LocalDateTime lastLogin;
}
