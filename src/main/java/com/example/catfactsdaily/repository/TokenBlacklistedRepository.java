package com.example.catfactsdaily.repository;

import com.example.catfactsdaily.entity.TokenBlacklisted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TokenBlacklistedRepository extends JpaRepository<TokenBlacklisted, Long> {

    Optional<TokenBlacklisted> findByToken(String token);
    List<TokenBlacklisted> findByExpirationDateBefore(Date now);
}
