package com.example.catfactsdaily.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tokens_blacklisted", uniqueConstraints = {@UniqueConstraint(columnNames = {"token"})}, indexes = {@Index(columnList = "token")})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenBlacklisted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expirationDate;
}
